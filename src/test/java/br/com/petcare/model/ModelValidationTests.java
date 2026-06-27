package br.com.petcare.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModelValidationTests {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void configurarValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void fecharValidator() {
        validatorFactory.close();
    }

    @Test
    void deveAceitarModelsValidos() {
        Usuario usuario = new Usuario("Pedro", "pedro@email.com", "123456");
        Proprietario proprietario = new Proprietario(
                "Maria", "(55) 99999-9999", "maria@email.com", "Rua Principal, 100"
        );
        Pet pet = new Pet("Rex", "Cachorro", "Vira-lata", 3, "Macho", null, 1);
        Consulta consulta = new Consulta(
                1,
                "2026-06-27T14:30",
                "Dra. Ana",
                "Consulta de rotina",
                new BigDecimal("150.00"),
                "Agendada",
                null
        );

        assertTrue(validator.validate(usuario).isEmpty());
        assertTrue(validator.validate(proprietario).isEmpty());
        assertTrue(validator.validate(pet).isEmpty());
        assertTrue(validator.validate(consulta).isEmpty());
    }

    @Test
    void deveRejeitarUsuarioInvalido() {
        Usuario usuario = new Usuario("", "email-invalido", "123");

        assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    void deveRejeitarProprietarioInvalido() {
        Proprietario proprietario = new Proprietario("", "", "email-invalido", "");

        assertFalse(validator.validate(proprietario).isEmpty());
    }

    @Test
    void devePermitirIdadeVaziaMasRejeitarIdadeNegativa() {
        Pet pet = new Pet("Rex", "Cachorro", null, null, "Macho", null, 1);
        assertTrue(validator.validate(pet).isEmpty());

        pet.setIdade(-1);
        assertFalse(validator.validate(pet).isEmpty());
    }

    @Test
    void deveExigirRelacionamentosValidos() {
        Pet pet = new Pet("Rex", "Cachorro", null, 3, "Macho", null, null);
        Consulta consulta = new Consulta(
                null,
                "2026-06-27T14:30",
                "Dra. Ana",
                "Consulta de rotina",
                BigDecimal.TEN,
                "Agendada",
                null
        );

        assertFalse(validator.validate(pet).isEmpty());
        assertFalse(validator.validate(consulta).isEmpty());
    }

    @Test
    void deveRejeitarValorEstimadoNegativo() {
        Consulta consulta = new Consulta(
                1,
                "2026-06-27T14:30",
                "Dra. Ana",
                "Consulta de rotina",
                new BigDecimal("-0.01"),
                "Agendada",
                null
        );

        assertFalse(validator.validate(consulta).isEmpty());
    }
}
