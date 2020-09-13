package ContaTest;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import Conta.Conta;

class ContaTest {

	/*
	 * Neste caso de teste, estamos verificando se � poss�vel s� a cria��o de contas
	 * com 5 d�gitos.
	 */
	@ParameterizedTest
	@ValueSource(strings = { "1234", "12345", "123456" })
	void testContaCriadaComMaisOuMenosQueCincoDigitos(String str) throws Exception {
		Conta conta = new Conta(str, 100);

		assertEquals(5, conta.getNumero().length());
	}

	/*
	 * Neste caso de teste, estamos verificando se quando uma conta � criada com o
	 * valor de saldo igual a zero, se realmente ela � criada.
	 */
	@Test
	void testContaCriadaComSaldoIgualAZero() throws Exception {
		Conta conta = new Conta("12345", 0);

		assertEquals(0, conta.getSaldo());
	}

	/*
	 * Neste caso de teste, estamos verificando se quando n�o especificamos o saldo
	 * no momento da cria��o da conta, o m�todo cria a conta com o saldo 0 por
	 * padr�o.
	 */
	@Test
	void testContaCriadaSemDefinirSaldo() throws Exception {
		Conta conta = new Conta("12345");

		assertEquals(0, conta.getSaldo());
	}

	/*
	 * Neste caso de teste, estamos verificando se quando tentamos creditar valores
	 * maiores ou menores que o permitido, o m�todo n�o poder� ser concluido.
	 */
	@ParameterizedTest
	@ValueSource(doubles = { -500, 0, 5000 })
	void testCreditarValorMaiorOuMenorQueOPermitido(double dbl) throws Exception {
		Conta conta = new Conta("12345", 100);

		Exception exception = assertThrows(Exception.class, () -> {

			conta.creditar(dbl);

		});

		assertEquals("credito imposs�vel!", exception.getMessage());
		assertEquals(100, conta.getSaldo());

	}

	/*
	 * Neste caso de teste, estamos verificando se quando debitamos um valor que
	 * deixe a conta negativada com mais de -300, o m�todo n�o ir� concluir o
	 * debito.
	 */
	@Test
	void testDebitarENaoFicarDevendoMaisQueTrezentosNegativo() throws Exception {
		Conta conta = new Conta("12345", 100);

		Exception exception = assertThrows(Exception.class, () -> {
			conta.debitar(500);

		});
		assertEquals("debito n�o realizado! saldo insuficiente em conta!", exception.getMessage());
		assertEquals(100, conta.getSaldo());
	}

}
