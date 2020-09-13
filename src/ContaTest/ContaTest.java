package ContaTest;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import Conta.Conta;

class ContaTest {

	/*
	 * Neste caso de teste, estamos verificando se é possível só a criação de contas
	 * com 5 dígitos.
	 */
	@ParameterizedTest
	@ValueSource(strings = { "1234", "12345", "123456" })
	void testContaCriadaComMaisOuMenosQueCincoDigitos(String str) throws Exception {
		Conta conta = new Conta(str, 100);

		assertEquals(5, conta.getNumero().length());
	}

	/*
	 * Neste caso de teste, estamos verificando se quando uma conta é criada com o
	 * valor de saldo igual a zero, se realmente ela é criada.
	 */
	@Test
	void testContaCriadaComSaldoIgualAZero() throws Exception {
		Conta conta = new Conta("12345", 0);

		assertEquals(0, conta.getSaldo());
	}

	/*
	 * Neste caso de teste, estamos verificando se quando não especificamos o saldo
	 * no momento da criação da conta, o método cria a conta com o saldo 0 por
	 * padrão.
	 */
	@Test
	void testContaCriadaSemDefinirSaldo() throws Exception {
		Conta conta = new Conta("12345");

		assertEquals(0, conta.getSaldo());
	}

	/*
	 * Neste caso de teste, estamos verificando se quando tentamos creditar valores
	 * maiores ou menores que o permitido, o método não poderá ser concluido.
	 */
	@ParameterizedTest
	@ValueSource(doubles = { -500, 0, 5000 })
	void testCreditarValorMaiorOuMenorQueOPermitido(double dbl) throws Exception {
		Conta conta = new Conta("12345", 100);

		Exception exception = assertThrows(Exception.class, () -> {

			conta.creditar(dbl);

		});

		assertEquals("credito impossível!", exception.getMessage());
		assertEquals(100, conta.getSaldo());

	}

	/*
	 * Neste caso de teste, estamos verificando se quando debitamos um valor que
	 * deixe a conta negativada com mais de -300, o método não irá concluir o
	 * debito.
	 */
	@Test
	void testDebitarENaoFicarDevendoMaisQueTrezentosNegativo() throws Exception {
		Conta conta = new Conta("12345", 100);

		Exception exception = assertThrows(Exception.class, () -> {
			conta.debitar(500);

		});
		assertEquals("debito não realizado! saldo insuficiente em conta!", exception.getMessage());
		assertEquals(100, conta.getSaldo());
	}

}
