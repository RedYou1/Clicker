package ca.RedYou.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * max +/- 9.999...e+6442450943
 * 
 * <br>
 * can have decimale too with as mush nines (but cant be a big number with a
 * precise decimal)
 * 
 * <br>
 * haven't tested all possibilites and what happend with overflows (probably
 * index out of bonds)
 * 
 * <br>
 * <br>
 * prototype started at : 2021/7/19 2PM <br>
 * prototype finished at : 2021/8/2 10PM
 * 
 * @author Jean-Christophe Demers CA/QC
 */
public class Quantity implements Comparable<Quantity> {

	private List<Integer> q;
	private int div;
	public boolean positif;

	/**
	 * contains 0
	 */
	public Quantity() {
		q = new ArrayList<Integer>();
		q.add(0);
		div = 0;
		positif = true;
	}

	/**
	 * duplicate
	 * 
	 * @param q
	 */
	public Quantity(Quantity q) {
		this.q = new ArrayList<>(q.q);
		this.positif = q.positif;
		this.div = q.div;
	}

	/**
	 * in = the rest
	 * 
	 * @param o the base
	 * @return the min exp
	 */
	private Quantity minLog10() {
		Quantity a = new Quantity();
		if (div > 0) {
			a.sub(valueOf(div));
			a.mult(valueOf(3));
			div = 0;
		}

		while (q.size() > 1) {
			a.add(valueOf(3));
			q.remove(0);
		}

		while (compareTo(valueOf(10)) >= 0) {
			a.add(valueOf(1));
			div(valueOf(10));
		}

		return a;
	}

	private int checkLog(Quantity base, Quantity n1, Quantity n2, Quantity to) {
		Quantity b = new Quantity(base);

		Quantity p = new Quantity(n1);
		p.add(new Quantity(n2));

		b.pow(p);

		return b.compareTo(new Quantity(to));
	}

	public void log10() {

		if (compareTo(new Quantity()) <= 0)
			throw new ArithmeticException("log can't be equal or below 0");

		Quantity rest = new Quantity(this);

		Quantity y = minLog10();
		Quantity ten = valueOf(10);

		if (checkLog(ten, y, valueOf(0), rest) == 0) {
			q = y.q;
			positif = y.positif;
			div = y.div;
			return;
		}

		Quantity a = valueOf(.5);
		Quantity a2 = valueOf(.5);
		Quantity max = valueOf(1);
		Quantity min = new Quantity();

		int diff;
		int maxini = 4;
		while (maxini > 0 && (diff = checkLog(ten, y, a, rest)) != 0) {
			if (diff > 0) {
				max = new Quantity(a2);

				a2.sub(new Quantity(min));
				a2.div(valueOf(2));
				a2.add(new Quantity(min));
			} else {
				min = new Quantity(a2);

				a2 = new Quantity(max);
				a2.sub(new Quantity(min));
				a2.div(valueOf(2));
				a2.add(new Quantity(min));
			}
			a = new Quantity(a2);
			maxini--;
//			a.round(-3);
		}

		a.add(y);

		q = a.q;
		positif = a.positif;
		div = a.div;
	}

	public void log(Quantity base) {

		if (base.compareTo(new Quantity()) <= 0)
			throw new ArithmeticException("the base of a log can't be equal or below 0");

		log10();
		if (base.equals(valueOf(10)))
			return;

		Quantity b = new Quantity(base);
		b.log10();
		div(b);
	}

	private int testSQRT(Quantity q, Quantity expo) {
		Quantity t = new Quantity(q);
		t.pow(new Quantity(expo));
		return t.compareTo(this);
	}

	private int testSQRT(Quantity q, Quantity a, Quantity expo) {
		Quantity t = new Quantity(q);
		t.add(new Quantity(a));
		t.pow(new Quantity(expo));
		t.sub(new Quantity(this));

		boolean m = t.compareTo(valueOf(1)) <= 0;
		boolean n = t.compareTo(valueOf(-1)) >= 0;

		if (m && n)
			return 0;
		if (!m)
			return 1;
		if (!n)
			return -1;

		throw new ArithmeticException("");
	}

	public void sqrt(Quantity expo) {

		int compto0 = compareTo(new Quantity());
		if (compto0 == 0)
			return;
		if (compto0 < 0)
			throw new ArithmeticException("sqrt can't be below 0");
		if (compareTo(valueOf(1)) == 0)
			return;

		Quantity one = valueOf(1);
		Quantity y = new Quantity(one);
		while (testSQRT(y, expo) < 0) {
			y.add(new Quantity(one));
		}

		if (testSQRT(y, expo) == 0) {
			q = y.q;
			positif = y.positif;
			div = y.div;
			return;
		}

		y.sub(new Quantity(one));

		Quantity a = valueOf(.5);
		Quantity max = valueOf(1);
		Quantity min = new Quantity();

		int diff;
		int maxini = 10;
		while (maxini > 0 && (diff = testSQRT(y, a, expo)) != 0) {
			if (diff > 0) {
				max = new Quantity(a);

				a.sub(new Quantity(min));
				a.div(valueOf(2));
				a.add(new Quantity(min));
			} else {
				min = new Quantity(a);

				a = new Quantity(max);
				a.sub(new Quantity(min));
				a.div(valueOf(2));
				a.add(new Quantity(min));
			}
			maxini--;
		}

		y.add(a);

		q = y.q;
		positif = y.positif;
		div = y.div;
	}

	// https://afteracademy.com/blog/calculate-power-function
	// (3. Optimized divide and conquer approach)
	public void pow(Quantity expo) {
		if (equals(new Quantity())) {
			q = new ArrayList<Integer>();
			q.add(0);
			div = 0;
			positif = true;
			return;
		}
		if (expo.compareTo(new Quantity()) < 0) {
			Quantity o = new Quantity(expo);
			o.positif = true;
			Quantity one = valueOf(1);
			one.div(o);
			pow(one);
			return;
		}

		if (equals(valueOf(1)) || expo.equals(new Quantity())) {
			q = new ArrayList<Integer>();
			q.add(1);
			div = 0;
			positif = true;
			return;
		}

		Quantity o = new Quantity(expo);
		o.floor(0);

		if (expo.div > 0) {
			boolean[] w = new boolean[] { false };

			Thread th = new Thread() {
				public void run() {
					pow(o);
					w[0] = true;
				}
			};
			th.start();

			Quantity t = new Quantity(expo);
			t.sub(o);

			Quantity temp = new Quantity(this);

			Quantity u = valueOf(1);
			for (int i = 0; i < t.div; i++) {
				u.q.add(0, 0);
			}
			t.div = 0;

			while (true) {
				for (int i = 10; i >= 2; i--) {
					final int a = i;
					Function<Quantity, Boolean> f = l -> {
						Quantity y = new Quantity(l);
						y.mod(valueOf(a));
						return y.equals(valueOf(0));
					};

					while (f.apply(u) && f.apply(t)) {
						u.div(valueOf(i));
						t.div(valueOf(i));
					}
				}

				t.update();
				u.update();
				if (t.q.size() == 1 && u.q.size() == 1)
					break;

				int a = t.q.get(0) % 2;
				int b = u.q.get(0) % 2;
				if (a != 0)
					t.sub(valueOf(1));
				if (b != 0)
					u.sub(valueOf(1));
			}
			temp.sqrt(u);
			temp.pow(t);

			while (!w[0])
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			mult(temp);
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

		int dfinal = div - i.div;

		div = 0;
		i.div = 0;

		Quantity rest = new Quantity();
		for (int j = q.size() - 1; j >= 0; j--) {
			rest.q.add(0, q.get(j));

			int a = 0;

			while (rest.compareTo(i) > -1) {
				a++;
				rest.sub(i);
			}

			if (a > 0)
				q.set(j, a);
			else if (j == q.size() - 1)
				q.remove(j);
		}
		if (i.q.size() < 5 && rest.compareTo(new Quantity()) > 0) {
			double d = rest.toDouble();
			d /= i.toDouble();
			Quantity qd = valueOf(d);
			qd.div += div;
			qd.update();
			add(qd);
		}

		while (dfinal < 0) {
			dfinal++;
			if (div > 0)
				div--;
			else
				q.add(0, 0);
		}
		div += dfinal;

		update();
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

		while (q.size() > 1 && q.get(q.size() - 1) == 0) {
			q.remove(q.size() - 1);
		}

		while (div > 0 && q.size() > 1 && q.get(0) == 0) {
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
				q.set(0, (int) (Math.round(q.get(0) / Math.pow(10, b)) * Math.pow(10, b)));
		} else {
			space *= -1;
			int a = (space + 2) / 3;
			int b = 2 - ((space - 1) % 3);

			int last = 0;
			while (div > a) {
				last = q.remove(0);
				div--;
			}

			if (b > 0)
				q.set(0, (int) (Math.round(q.get(0) / Math.pow(10, b)) * Math.pow(10, b)));

			if (last >= 500) {
				q.set(0, q.get(0) + 1);
				update();
			}
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
	public void ceil(int space) {
		update();
		if (div < (-space) / 3 && space < 0)
			return;

		if (space >= 0) {
			if (div >= q.size()) {
				q = new ArrayList<Integer>();
				div = 0;
				positif = true;
				return;
			}

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
				q.set(0, (int) (Math.ceil(q.get(0) / Math.pow(10, b)) * Math.pow(10, b)));
		} else {
			space *= -1;
			int a = (space + 2) / 3;
			int b = 2 - ((space - 1) % 3);

			while (div > a) {
				q.remove(0);
				div--;
			}

			if (b > 0)
				q.set(0, (int) (Math.ceil(q.get(0) / Math.pow(10, b)) * Math.pow(10, b)));
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

		if (Math.max(div, q.size() - div) > Math.max(div, o.q.size() - o.div))
			return 1;
		if (Math.max(div, q.size() - div) < Math.max(div, o.q.size() - o.div))
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
			if (i < div) {
				while (t.length() < 3)
					t = "0" + t;

				if (i == 0)
					while (t.endsWith("0"))
						t = t.substring(0, t.length() - 1);
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

		if (q.size() == 1 && div <= 1) {
			String s = (positif ? "" : "-") + (q.get(0) / Math.pow(1000, div));
			if (s.endsWith(".0"))
				s = s.substring(0, s.length() - 2);
			return s;
		}

		String h = "";

		int size = q.size() - div;
		boolean tsize = size <= 0;
		if (tsize) {
			size *= -1;
			size += 2;
		}
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