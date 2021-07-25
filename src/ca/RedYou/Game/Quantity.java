package ca.RedYou.Game;

import java.util.ArrayList;
import java.util.List;

public class Quantity implements Comparable<Quantity> {

	private List<Integer> q;
	private int div;
	public boolean positif;

	public Quantity() {
		q = new ArrayList<Integer>();
		q.add(0);
		div = 0;
		positif = true;
	}

	public Quantity(Quantity q) {
		this.q = new ArrayList<>(q.q);
		this.positif = q.positif;
		this.div = q.div;
	}

	// https://afteracademy.com/blog/calculate-power-function
	// (3. Optimized divide and conquer approach)
	public void pow(Quantity expo) {
		Quantity o = new Quantity(expo);
		o.floor(0);
		o.update();

		if (o.equals(new Quantity())) {
			q = new ArrayList<Integer>();
			q.add(1);
			div = 0;
			positif = true;
			return;
		}

		Quantity t = new Quantity(o);
		t.div(Quantity.valueOf(2));
		t.floor(0);
		Quantity temp = new Quantity(this);
		temp.pow(t);

		Quantity nexpo = new Quantity(o);
		nexpo.mod(Quantity.valueOf(2));

		temp.mult(new Quantity(temp));

		int a = nexpo.compareTo(new Quantity());
		if (a > 0)
			mult(new Quantity(temp));
		else if (a == 0) {
			q = temp.q;
			positif = true;
			this.div = temp.div;
		} else {
			temp.div(new Quantity(this));
			q = temp.q;
			positif = temp.positif;
			this.div = temp.div;
		}
		System.out.println(this + "/" + expo);

		update();

	}

	public void mod(Quantity i) {
		if (i.equals(new Quantity())) {
			throw new ArithmeticException("You can't divide by 0!");
		}
		if (i.compareTo(new Quantity()) < 0) {
			positif = !positif;
		}
		i.positif = true;
		Quantity rest = new Quantity();

		for (int j = q.size() - 1; j >= 0; j--) {
			rest.add(valueOf(q.get(j)));

			int a = 0;

			while (rest.compareTo(i) > -1) {
				a++;
				i.positif = false;
				rest.add(i);
				i.positif = true;
			}

			if (a > 0)
				q.set(j, a);
			else
				q.remove(j);

			if (j > 0)
				rest.q.add(0, 0);
		}

		q = rest.q;
		positif = rest.positif;
	}

	public void div(Quantity i) {
		i = new Quantity(i);
		if (i.equals(new Quantity())) {
			throw new ArithmeticException("You can't divide by 0!");
		}
		if (i.compareTo(new Quantity()) < 0) {
			positif = !positif;
		}
		i.positif = true;
		Quantity rest = new Quantity();
		rest.q = new ArrayList<Integer>();

		for (int j = q.size() - 1; j >= 0; j--) {
			rest.q.add(0, q.get(j));

			int a = 0;

			while (rest.compareTo(i) > -1) {
				a++;
				rest.sub(i);
			}

			if (a > 0)
				q.set(j, a);
			else
				q.remove(j);
		}

		if (i.q.size() < 5) {
			double d = rest.toDouble();
			d /= i.toDouble();
			add(valueOf(d));
		}
	}

	public void mult(Quantity i) {
		int ticomp = i.compareTo(new Quantity());
		if (ticomp == 0) {
			q = new ArrayList<>();
			q.add(0);
			positif = true;
			div = 0;
			return;
		}
		if (positif == i.positif) {
			positif = true;
		} else {
			positif = false;
		}

		Quantity rep = new Quantity();
		Quantity t = new Quantity(this);
		t.div = 0;
		for (int l = 0; l < i.q.size(); l++) {
			Quantity mult = new Quantity();
			int a = i.q.get(l);
			if (a > 0) {
				for (int k = 0; k < a; k++) {
					mult.add(t);
				}
				for (int j = 0; j < l; j++) {
					mult.q.add(0, 0);
				}
				rep.add(mult);
			}
		}

		q = rep.q;
		div += i.div;
		update();
	}

	public void sub(Quantity o) {
		Quantity q = new Quantity(o);
		q.positif = !q.positif;
		add(q);
	}

	public void add(Quantity o) {
		o = new Quantity(o);
		while (div < o.div) {
			q.add(0, 0);
			div++;
		}

		while (div > o.div) {
			o.q.add(0, 0);
			o.div++;
		}

		if (positif == o.positif)
			for (int i = 0; i < o.q.size(); i++) {
				if (i >= q.size())
					q.add(o.q.get(i));
				else
					q.set(i, q.get(i) + o.q.get(i));
			}
		else
			for (int i = 0; i < o.q.size(); i++) {
				if (i >= q.size()) {
					positif = o.positif;
					q.add(o.q.get(i));
				} else {
					q.set(i, q.get(i) - o.q.get(i));
					if (q.get(i) < 0) {
						if (i + 1 == q.size()) {
							positif = o.positif;
							q.set(i, q.get(i) * -1);
						} else {
							q.set(i, q.get(i) + 1000);
							q.set(i + 1, q.get(i + 1) - 1);
						}
					}
				}
			}
		update();
	}

	private void update() {
		if (q.size() == 0) {
			q.add(0);
			div = 0;
			positif = true;
			return;
		}
		for (int i = 0; i < q.size(); i++) {
			if (q.get(i) < 0) {
				if (q.size() <= i + 1) {
					q.set(i, -1 * q.get(i));
					positif = !positif;
					break;
				}

				int qt = ((-1 * q.get(i)) / 1000) + 1;
				q.set(i, q.get(i) + (qt * 1000));
				q.set(i + 1, q.get(i + 1) - qt);
			}

			if (q.get(i) >= 1000) {
				int qt = q.get(i) / 1000;
				q.set(i, q.get(i) - (qt * 1000));
				if (q.size() == i + 1)
					q.add(qt);
				else
					q.set(i + 1, q.get(i + 1) + qt);
			}
		}

		while (q.size() > 0 && q.get(q.size() - 1) == 0) {
			q.remove(q.size() - 1);
		}

		while (div > 0 && q.size() > 0 && q.get(0) == 0) {
			q.remove(0);
			div--;
		}
	}

	/**
	 * 
	 * @param space <br>
	 *              <ul>
	 *              <li>...</li>
	 *              <li>3=1000</li>
	 *              <li>2=100</li>
	 *              <li>1=10</li>
	 *              <li>0=1</li>
	 *              <li>-1=0.1</li>
	 *              <li>-2=0.01</li>
	 *              <li>-3=0.001</li>
	 *              <li>...</li>
	 *              </ul>
	 */
	public void round(int space) {
		if (div < (-space) / 3 && space < 0)
			return;

		floor(space - 1);

		if (space < 0) {
			int s = (-space) - 1;
			int a = s / 3;
			int b = (s - 1) / 3;
			if (a == b) {
				double i = q.get(0);
				i /= Math.pow(10, (2 - s) % 3);
				i = Math.round(i) * Math.pow(10, (2 - s) % 3);
				q.set(0, (int) i);
			} else if (q.get(0) >= 500)
				q.set(1, q.get(1) + 1);

		} else if (space == 0 && div >= 1 && q.get(div - 1) >= 500)
			q.set(div, q.get(div) + 1);

		else if (space > 0) {
			int a = space / 3;
			int b = (space - 1) / 3;
			if (a == b) {
				double i = q.get(a);
				i /= Math.pow(10, space % 3);
				i = Math.round(i) * Math.pow(10, space % 3);
				q.set(a, (int) i);
			} else if (q.get(b) >= 500)
				q.set(a, q.get(a) + 1);
		}
		update();

		floor(space);
	}

	/**
	 * 
	 * @param space <br>
	 *              <ul>
	 *              <li>...</li>
	 *              <li>3=1000</li>
	 *              <li>2=100</li>
	 *              <li>1=10</li>
	 *              <li>0=1</li>
	 *              <li>-1=0.1</li>
	 *              <li>-2=0.01</li>
	 *              <li>-3=0.001</li>
	 *              <li>...</li>
	 *              </ul>
	 */
	public void floor(int space) {
		if (div < (-space) / 3 && space < 0)
			return;

		if (space >= 0) {
			int a = space / 3;
			int b = space % 3;

			while (div > 0) {
				q.remove(0);
				div--;
			}

			for (int i = 0; i < a; i++) {
				q.set(i, 0);
			}

			if (b > 0)
				q.set(0, (int) (Math.floor(q.get(0) / Math.pow(10, b)) * Math.pow(10, b)));
		} else {
			space *= -1;
			int a = (space + 2) / 3;
			int b = 2 - ((space - 1) % 3);

			while (div > a) {
				q.remove(0);
				div--;
			}

			if (b > 0)
				q.set(0, (int) (Math.floor(q.get(0) / Math.pow(10, b)) * Math.pow(10, b)));
		}
	}

	public static Quantity valueOf(int i) {
		return valueOf((long) i);
	}

	public static Quantity valueOf(long i) {
		Quantity q = new Quantity();

		if (i == 0)
			return q;
		if (i < 0) {
			i *= -1;
			q.positif = false;
		}

		q.q = new ArrayList<>();
		while (i > 0) {
			q.q.add((int) (i % 1000));
			i /= 1000;
		}
		return q;
	}

	public static Quantity valueOf(float i) {
		return valueOf((double) i);
	}

	public static Quantity valueOf(double i) {
		Quantity q = new Quantity();

		if (i == 0)
			return q;
		if (i < 0) {
			i *= -1;
			q.positif = false;
		}

		while (i - ((long) i) > 0) {
			i *= 1000;
			q.div++;
		}

		long a = (long) i;

		q.q = new ArrayList<>();
		while (a > 0) {
			q.q.add((int) (a % 1000));
			a /= 1000;
		}
		return q;
	}

	public static Quantity valueOf(String s) {
		Quantity q = new Quantity();
		q.positif = true;
		if (s.startsWith("-")) {
			s = s.substring(1, s.length());
			q.positif = false;
		}

		q.q = new ArrayList<Integer>();

		while (s.length() > 0) {
			if (s.charAt(s.length() - 1) == '.') {
				if (q.div != 0)
					throw new ArithmeticException("you cant have multiple \'.\' in a single number");
				q.div = q.q.size();
				s = s.substring(0, s.length() - 1);
			}

			String a = s.substring(s.length() - Math.min(s.length(), 3), s.length());

			q.q.add(Integer.valueOf(a));

			s = s.substring(0, s.length() - a.length());
		}

		return q;
	}

	@Override
	public int compareTo(Quantity o) {

		update();
		o.update();

		if (positif && !o.positif)
			return 1;
		if (!positif && o.positif)
			return -1;

		if (q.size() - div > o.q.size() - o.div)
			return 1;
		if (q.size() - div < o.q.size() - o.div)
			return -1;

		for (int i = 1; i <= q.size() && i <= o.q.size(); i++) {
			if (q.get(q.size() - i) > o.q.get(o.q.size() - i))
				return 1;
			if (q.get(q.size() - i) < o.q.get(o.q.size() - i))
				return -1;
		}

		if (div > o.div)
			return 1;
		if (div < o.div)
			return -1;

		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Quantity) {
			return compareTo((Quantity) o) == 0;
		}
		return false;
	}

	public String fullString() {
		update();
		String s = "";
		if (!positif)
			s += "-";
		for (int i = q.size() - 1; i >= 0; i--) {
			if (i + 1 == div)
				s += ".";

			String t = String.valueOf(q.get(i));
			if (i != q.size() - 1)
				while (t.length() < 3) {
					t = "0" + t;
				}
			s += t;
		}
		return s;
	}

	public long toLong() {
		long l = 0;
		for (int i = 0; i < q.size(); i++) {
			long a = q.get(i);
			a *= Math.pow(1000, i);
			l += a;
		}
		return (long) (l / Math.pow(1000, div));
	}

	public double toDouble() {
		long l = 0;
		for (int i = 0; i < q.size(); i++) {
			long a = q.get(i);
			a *= Math.pow(1000, i);
			l += a;
		}
		return ((double) l) / Math.pow(1000, div);
	}

	@Override
	public String toString() {
		if (q.size() == 0)
			return "0";

		if (q.size() == 1) {
			String s = (positif ? "" : "-") + (q.get(0) / Math.pow(1000, div));
			if (s.endsWith(".0"))
				s = s.substring(0, s.length() - 2);
			return s;
		}

		String h = "";

		int size = q.size() - div;
		boolean tsize = size < 0;
		if (tsize)
			size *= -1;
		List<Character> s = new ArrayList<Character>();
		s.add((char) 96);
		if (size > 1) {
			while (size > 1) {
				int i = 0;
				while (true) {
					s.set(i, (char) (s.get(i) + 1));
					if (s.get(i) > 122) {
						s.set(i, (char) 97);
						s.add((char) 97);
						i++;
					} else {
						break;
					}
				}
				size--;
			}
			for (int i = 0; i < s.size(); i++) {
				h = s.get(i) + h;
			}
			if (tsize)
				h = " -" + h;
		}

		String t = String.valueOf(q.get(q.size() - 2));
		if (t != "0")
			while (t.length() < 3) {
				t = "0" + t;
			}
		while (t.endsWith("0")) {
			t = t.substring(0, t.length() - 1);
		}
		return (positif ? "" : "-") + q.get(q.size() - 1) + (t.length() == 0 || t.equalsIgnoreCase("0") ? "" : ".") + t
				+ h;
	}
}