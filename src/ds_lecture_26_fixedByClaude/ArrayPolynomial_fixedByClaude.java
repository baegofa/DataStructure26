package ds_lecture_26_fixedByClaude;

import java.util.ArrayList;

/**
 * 배열(ArrayList)로 구현한 다항식.
 * 불변식: 항은 지수 내림차순으로 저장하고, 계수가 0 인 항은 저장하지 않는다.
 */
public class ArrayPolynomial_fixedByClaude implements Polynomial_fixedByClaude {
	private final ArrayList<PolynomialTerm_fixedByClaude> termArray = new ArrayList<>();

	public ArrayPolynomial_fixedByClaude() {}	// 영 다항식 (원본의 Zero())

	// 내림차순이 이미 보장될 때만 쓰는 내부용 append
	private void appendTerm(double coef, int exp) {
		if (coef != 0)
			termArray.add(new PolynomialTerm_fixedByClaude(coef, exp));
	}

	@Override
	public boolean isZero() {
		return termArray.isEmpty();
	}

	@Override
	public double coef(int exp) {
		for (PolynomialTerm_fixedByClaude t : termArray)
			if (t.exp == exp) return t.coef;
		return 0;
	}

	@Override
	public int leadExp() {
		if (isZero())
			throw new IllegalStateException("영 다항식은 최고차항이 없습니다.");
		return termArray.get(0).exp;
	}

	/** coef·x^exp 를 이 다항식에 더한다. 위치는 지수 순서에 맞게 자동으로 찾는다. */
	@Override
	public ArrayPolynomial_fixedByClaude attach(double coef, int exp) {
		if (coef == 0) return this;
		int i = 0;
		while (i < termArray.size() && termArray.get(i).exp > exp)
			i++;
		if (i < termArray.size() && termArray.get(i).exp == exp) {
			double sum = termArray.get(i).coef + coef;
			if (sum == 0) termArray.remove(i);
			else termArray.set(i, new PolynomialTerm_fixedByClaude(sum, exp));
		}
		else
			termArray.add(i, new PolynomialTerm_fixedByClaude(coef, exp));
		return this;
	}

	@Override
	public ArrayPolynomial_fixedByClaude remove(int exp) {
		termArray.removeIf(t -> t.exp == exp);
		return this;
	}

	@Override
	public ArrayPolynomial_fixedByClaude singleMult(double coef, int exp) {
		ArrayPolynomial_fixedByClaude D = new ArrayPolynomial_fixedByClaude();
		for (PolynomialTerm_fixedByClaude t : termArray)
			D.appendTerm(t.coef * coef, t.exp + exp);	// 모든 지수에 같은 값을 더하므로 순서 유지
		return D;
	}

	// FIX: 원본은 Add(Polynomial) 이 null 을 반환하고, 실제 로직은 오버로드된 Add(ArrayPolynomial) 에 있었다.
	@Override
	public ArrayPolynomial_fixedByClaude add(Polynomial_fixedByClaude other) {
		ArrayPolynomial_fixedByClaude B = cast(other);
		ArrayPolynomial_fixedByClaude D = new ArrayPolynomial_fixedByClaude();

		int posA = 0, posB = 0;
		while (posA < termArray.size() && posB < B.termArray.size()) {
			PolynomialTerm_fixedByClaude termA = termArray.get(posA);
			PolynomialTerm_fixedByClaude termB = B.termArray.get(posB);	// FIX: 원본은 this 의 termArray 에서 꺼냈다
			int cmp = Integer.compare(termA.exp, termB.exp);
			if (cmp > 0) {
				D.termArray.add(termA);
				posA++;
			}
			else if (cmp < 0) {
				D.termArray.add(termB);
				posB++;
			}
			else {
				D.appendTerm(termA.coef + termB.coef, termA.exp);	// 합이 0 이면 appendTerm 이 버린다
				posA++;
				posB++;
			}
		}

		for (; posA < termArray.size(); posA++)
			D.termArray.add(termArray.get(posA));
		for (; posB < B.termArray.size(); posB++)	// FIX: 원본은 B 가 아니라 this 의 크기/항을 썼다
			D.termArray.add(B.termArray.get(posB));

		return D;
	}

	@Override
	public ArrayPolynomial_fixedByClaude mult(Polynomial_fixedByClaude other) {
		ArrayPolynomial_fixedByClaude B = cast(other);
		ArrayPolynomial_fixedByClaude D = new ArrayPolynomial_fixedByClaude();
		for (PolynomialTerm_fixedByClaude t : termArray)
			D = D.add(B.singleMult(t.coef, t.exp));
		return D;
	}

	private static ArrayPolynomial_fixedByClaude cast(Polynomial_fixedByClaude p) {
		if (!(p instanceof ArrayPolynomial_fixedByClaude))
			throw new IllegalArgumentException("ArrayPolynomial 끼리만 연산할 수 있습니다.");
		return (ArrayPolynomial_fixedByClaude) p;
	}

	@Override
	public String toString() {
		if (isZero()) return "0";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < termArray.size(); i++) {
			if (i > 0) sb.append(" + ");
			sb.append(termArray.get(i));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		ArrayPolynomial_fixedByClaude A = new ArrayPolynomial_fixedByClaude();
		A.attach(5, 3).attach(10, 4);	// 순서 상관없이 넣어도 내림차순으로 정리된다
		ArrayPolynomial_fixedByClaude B = new ArrayPolynomial_fixedByClaude();
		B.attach(25, 4).attach(2, 2).attach(1, 1).attach(7, 0);

		System.out.println("A     = " + A);	// 10.0x^4 + 5.0x^3
		System.out.println("B     = " + B);	// 25.0x^4 + 2.0x^2 + 1.0x + 7.0
		System.out.println("A + B = " + A.add(B));	// 35.0x^4 + 5.0x^3 + 2.0x^2 + 1.0x + 7.0
		System.out.println("A * B = " + A.mult(B));
		System.out.println("coef(4) of B = " + B.coef(4));	// 25.0
		System.out.println("leadExp of A = " + A.leadExp());	// 4
		System.out.println("A + (-A) = " + A.add(A.singleMult(-1, 0)));	// 0
	}
}
