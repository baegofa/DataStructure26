package ds_lecture_26_fixedByClaude;

/**
 * 다항식 ADT.
 * FIX: 원본은 Coef(Polynomial poly, int exp) 처럼 인스턴스 메서드가 다른 다항식을 인자로 받아
 *      this 와 poly 중 무엇을 다루는지 모호했다. 모든 연산을 this 기준으로 바꿨다.
 *      Zero() 는 생성자(new ...())가 그 역할을 하므로 뺐다.
 */
public interface Polynomial_fixedByClaude {
	boolean isZero();
	double coef(int exp);
	int leadExp();
	Polynomial_fixedByClaude attach(double coef, int exp);
	Polynomial_fixedByClaude remove(int exp);
	Polynomial_fixedByClaude singleMult(double coef, int exp);
	Polynomial_fixedByClaude add(Polynomial_fixedByClaude other);
	Polynomial_fixedByClaude mult(Polynomial_fixedByClaude other);
}
