package ds_lecture_26_fixedByClaude;

// FIX: 원본은 ArrayPolynomial.java 안의 보조 클래스였는데 LinkedListPolynomials 에서도 쓰므로 별도 파일로 분리했다.
// FIX: 필드를 final 로 두어 한 번 만든 항은 바뀌지 않게 했다 (여러 다항식이 같은 항을 공유해도 안전).
public class PolynomialTerm_fixedByClaude {
	public final double coef;
	public final int exp;

	public PolynomialTerm_fixedByClaude(double coef, int exp) {
		this.coef = coef;
		this.exp = exp;
	}

	// 예: 3.0x^4, -2.0x, 1.0
	@Override
	public String toString() {
		if (exp == 0) return String.valueOf(coef);
		if (exp == 1) return coef + "x";
		return coef + "x^" + exp;
	}
}
