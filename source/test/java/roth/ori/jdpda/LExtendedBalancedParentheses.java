package roth.ori.jdpda;

import static roth.ori.jdpda.LExtendedBalancedParentheses.Letter.RP;
import static roth.ori.jdpda.LExtendedBalancedParentheses.Letter.lp;
import static roth.ori.jdpda.LExtendedBalancedParentheses.Letter.rp;
import static roth.ori.jdpda.LExtendedBalancedParentheses.StackSymbol.$;
import static roth.ori.jdpda.LExtendedBalancedParentheses.StackSymbol.X;
import static roth.ori.jdpda.LExtendedBalancedParentheses.State.q0;
import static roth.ori.jdpda.LExtendedBalancedParentheses.State.q1;
import static roth.ori.jdpda.LExtendedBalancedParentheses.State.q2;
import static roth.ori.jdpda.generated.LExtendedBalancedParenthesesAPI.START;

public class LExtendedBalancedParentheses {
	enum State {
		q0, q1, q2
	}

	enum Letter {
		lp, rp, RP
	}

	enum StackSymbol {
		$, X
	}

	public static DPDA<State, Letter, StackSymbol> M = new DPDA.Builder<>(State.class, Letter.class, StackSymbol.class) //
			.δ(q0, lp, $, q1, $, X) //
			.δ(q1, lp, X, q1, X, X) //
			.δ(q1, rp, X, q1) //
			.δ(q1, null, $, q0, $) //
			.δ(q1, RP, X, q2) //
			.δ(q2, null, X, q2) //
			.δ(q2, null, $, q0, $) //
			.q0(q0) //
			.q$(q0) //
			.γ0($) //
			.go();

	public static void main(String[] args) {
		START().lp().rp().ACCEPT();
		START().lp().rp().rp().STUCK();
		START().lp().lp().lp().rp().rp().TERMINATED();
		START().lp().lp().lp().rp().rp().rp().ACCEPT();
		START().lp().lp().lp().rp().RP().ACCEPT();
	}
}
