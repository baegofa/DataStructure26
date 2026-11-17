package ds_lecture_26_fixedByClaude;

import java.util.Iterator;

public class LinkedListPolynomials_fixedByClaude {
	private final SinglyLinkedList_fixedByClaude<PolynomialTerm_fixedByClaude> list;
	// FIX: 쓰이지 않던 public 필드 sum 을 제거했다 (add 의 지역 변수 sum 과 이름만 겹쳤다).

	public LinkedListPolynomials_fixedByClaude() {
		list = new SinglyLinkedList_fixedByClaude<>();
	}

	/** 항을 맨 뒤에 붙인다. 지수 내림차순으로 넣어야 한다. */
	public void append(double coef, int exp) {
		append(new PolynomialTerm_fixedByClaude(coef, exp));
	}

	public void append(PolynomialTerm_fixedByClaude e) {
		if (e.coef == 0) return;	// FIX: 계수 0 인 항은 저장하지 않는다
		if (!list.isEmpty() && list.last.item.exp <= e.exp)	// FIX: 순서가 깨지면 add 가 틀리므로 바로 알린다
			throw new IllegalArgumentException("지수는 내림차순으로 append 해야 합니다: " + e.exp);
		list.addLast(e);
	}

	/** 소진되면 null 을 돌려주는 next. hasNext 검사를 한 곳으로 모은다. */
	private static PolynomialTerm_fixedByClaude nextOrNull(Iterator<PolynomialTerm_fixedByClaude> iter) {
		return iter.hasNext() ? iter.next() : null;
	}

	/** this + poly 를 새 다항식으로 반환한다. 두 다항식 모두 지수 내림차순이어야 한다. */
	public LinkedListPolynomials_fixedByClaude add(LinkedListPolynomials_fixedByClaude poly) {
		LinkedListPolynomials_fixedByClaude sum = new LinkedListPolynomials_fixedByClaude();
		Iterator<PolynomialTerm_fixedByClaude> thisIter = this.list.iterator();
		Iterator<PolynomialTerm_fixedByClaude> polyIter = poly.list.iterator();

		PolynomialTerm_fixedByClaude tempThis = nextOrNull(thisIter);
		PolynomialTerm_fixedByClaude tempPoly = nextOrNull(polyIter);

		while (tempThis != null || tempPoly != null) {
			// 원본의 다섯 갈래를 "누구 항을 먼저 넣나" 기준의 세 갈래로 합쳤다
			if (tempPoly == null || (tempThis != null && tempThis.exp > tempPoly.exp)) {
				sum.append(tempThis);	// 항이 불변이므로 새로 만들지 않고 공유한다
				tempThis = nextOrNull(thisIter);
			}
			else if (tempThis == null || tempThis.exp < tempPoly.exp) {
				sum.append(tempPoly);
				tempPoly = nextOrNull(polyIter);
			}
			else {	// 지수가 같으면 계수를 합친다 (0 이 되면 append 가 버린다)
				sum.append(tempThis.coef + tempPoly.coef, tempThis.exp);
				tempThis = nextOrNull(thisIter);
				tempPoly = nextOrNull(polyIter);
			}
		}
		return sum;
	}

	// FIX: 원본 show() 는 "10.0^4 + 5.0^3 + " 처럼 x 가 빠지고 끝에 " + " 가 남았다.
	@Override
	public String toString() {
		if (list.isEmpty()) return "0";
		StringBuilder sb = new StringBuilder();
		for (PolynomialTerm_fixedByClaude t : list) {
			if (sb.length() > 0) sb.append(" + ");
			sb.append(t);
		}
		return sb.toString();
	}

	public void show() {
		System.out.println(this);
	}

	public static void main(String[] args) {
		LinkedListPolynomials_fixedByClaude tess = new LinkedListPolynomials_fixedByClaude();
		LinkedListPolynomials_fixedByClaude toss = new LinkedListPolynomials_fixedByClaude();
		tess.append(10, 4);
		tess.append(5, 3);

		toss.append(25, 4);
		toss.append(2, 2);
		toss.append(1, 1);

		// FIX: 원본은 결과를 버리는 tess.add(toss) 를 한 번 더 호출했다.
		LinkedListPolynomials_fixedByClaude result = tess.add(toss);
		System.out.println("tess = " + tess);
		System.out.println("toss = " + toss);
		result.show();	// 35.0x^4 + 5.0x^3 + 2.0x^2 + 1.0x
	}
}
