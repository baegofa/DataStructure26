package ds_lecture_26;

import java.util.ArrayList;

class PolynomialTerm {
	public double coef;
	public int exp;
	
	public PolynomialTerm(double coef, int exp) {
		this.coef = coef;
		this.exp = exp;
	}
	
	public String toString() {
		return "("+"coef="+coef+",exp="+exp+')';
	}
}

public class ArrayPolynomial implements Polynomial{
	private final ArrayList<PolynomialTerm> termArray;
	
	public ArrayPolynomial() {
		termArray = new ArrayList<>();
	}
	
	public void appendTerm(double coef, int exp) {
		termArray.add(new PolynomialTerm(coef,exp));
	}
	
	@Override
	public String toString() {
		return "다항식:"+termArray;
	}

	@Override
	public Polynomial Zero() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean IsZero(Polynomial poly) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double Coef(Polynomial poly, int exp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int LeadExp(Polynomial poly) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Polynomial Attach(Polynomial poly, double coef, int exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial Remove(Polynomial poly, int exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial SingleMult(Polynomial poly, double coef, int exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayPolynomial Add(Polynomial B) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayPolynomial Add(ArrayPolynomial B) {
		// TODO Auto-generated method stub
		ArrayPolynomial D = new ArrayPolynomial();
		
		int posA = 0, posB = 0;
		double sum;
		while(posA < termArray.size() && posB < B.termArray.size()) {
			PolynomialTerm termA = termArray.get(posA);
			PolynomialTerm termB = termArray.get(posB);
			switch(Integer.compare(termA.exp, termB.exp)) {
			case 1: 
				D.appendTerm(termA.coef, termA.exp);
				posA++;
				break;
			case -1:
				D.appendTerm(termB.coef,  termB.exp);
				posB++;
				break;
			case 0:
				double tempSum = termA.coef + termB.coef;
				if(tempSum != 0)D.appendTerm(tempSum, termA.exp);
				posA++;
				posB++;
				break;
			}
		}
		
		for(;posA<termArray.size();posA++)
			D.appendTerm(termArray.get(posA).coef, termArray.get(posA).exp);
		for(;posB<termArray.size();posB++)
			D.appendTerm(termArray.get(posB).coef, termArray.get(posB).exp);
		
		return D;
	}

	@Override
	public Polynomial Mult(Polynomial poly1, Polynomial poly2) {
		// TODO Auto-generated method stub
		return null;
	}

}
