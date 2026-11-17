package ds_lecture_26;

public interface Polynomial {
	Polynomial Zero();
	boolean IsZero(Polynomial poly);
	double Coef(Polynomial poly, int exp);
	int LeadExp(Polynomial poly);
	Polynomial Attach(Polynomial poly, double coef, int exp);
	Polynomial Remove(Polynomial poly, int exp);
	Polynomial SingleMult(Polynomial poly, double coef, int exp);
	Polynomial Add(Polynomial poly1);
	Polynomial Mult(Polynomial poly1, Polynomial poly2);
}
