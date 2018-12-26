package jdpda;

import static jdpda.LAStarB.Letter.a;
import static jdpda.LAStarB.Letter.b;
import static jdpda.LAStarB.StackSymbol.X;
import static jdpda.LAStarB.State.q0;
import static jdpda.LAStarB.State.q1;
import static jdpda.generated.LAStarBAPI.START;

import jdpda.DPDA;

public class LAStarB {
	enum State {
		q0, q1
	}

	enum Letter {
		a, b
	}

	enum StackSymbol {
		X
	}

	public static DPDA<State, Letter, StackSymbol> M = new DPDA.Builder<>(State.class, Letter.class, StackSymbol.class) //
			.δ(q0, a, X, q0, X, X) //
			.δ(q0, b, X, q1) //
			.δ(q1, null, X, q1) //
			.q0(q0) //
			.F(q1) //
			.γ0(X) //
			.go();

	public static void main(String[] args) {
		START().a().a().a().b().ACCEPT();
		START().b().ACCEPT();
	}
}