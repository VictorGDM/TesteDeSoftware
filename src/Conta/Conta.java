package Conta;

public class Conta {
	private String numero;
	private double saldo;

	public Conta(String numero, double saldo) throws Exception {
		for (int i = 0; i < numero.length() - 1; i++) {
			if (!Character.isDigit(numero.charAt(i)))
				throw new Exception("Conta deve possuir apenas d�gitos. Conta inv�lida!");
		}
		if (saldo <= 0)
			throw new Exception("Saldo nulo ou negativo. Conta inv�lida!");
		this.numero = numero;
		this.saldo = saldo;
	}

	public Conta(String numero) throws Exception {
		this(numero, 0);
	}

	public void creditar(double valor) throws Exception {
		if (valor <= 0 || valor > 1000)
			throw new Exception("credito imposs�vel!");
		saldo += valor;
	}

	public void debitar(double valor) throws Exception {
		if (valor <= 0)
			throw new Exception("debito imposs�vel!");
		saldo -= valor;
		if (saldo < -300)
			throw new Exception("debito n�o realizado! saldo insuficiente em conta!");
	}

	public double getSaldo() {
		return saldo;
	}

	public String getNumero() {
		return numero;
	}
}