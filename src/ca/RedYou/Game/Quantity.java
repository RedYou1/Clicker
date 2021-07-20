package ca.RedYou.Game;

import java.util.ArrayList;
import java.util.List;

public class Quantity extends Number implements Comparable<Quantity> {
	private static final long serialVersionUID = 1L;

	private List<Integer> q;
	public boolean positif;

	public Quantity() {
		q = new ArrayList<Integer>();
		q.add(0);
		positif = true;
	}

	public Quantity(String s) {
		positif = true;
		if (s.startsWith("-")) {
			s = s.replace("-", "");
			positif = false;
		}
		q = new ArrayList<Integer>();

		int i = s.length() - 1;
		for (; i >= 3; i -= 3) {
			q.add(Integer.valueOf(s.substring(i - 2, i + 1)));
		}
		if (i < 3) {
			q.add(Integer.valueOf(s.substring(0, i + 1)));
		}
	}

	public Quantity(List<Integer> q, boolean p) {
		this.q = q;
		positif = p;
	}

	public Quantity(Quantity q) {
		this.q = new ArrayList<>(q.q);
		this.positif = q.positif;
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
			rest.addOrSub(q.get(j));

			int a = 0;

			while (rest.compareTo(i) > -1) {
				a++;
				i.positif = false;
				rest.addOrSub(i);
				i.positif = true;
			}

			if (a > 0)
				q.set(j, a);
			else
				q.remove(j);

			if (j > 0)
				rest.mult(1000);
		}

		q = rest.q;
		positif = rest.positif;
	}

	public void mod(int i) {
		Quantity q = new Quantity(this);
		q.div(i);
		q.mult(-i);
		addOrSub(q);
	}

	public void div(Quantity i) {
		if (i.equals(new Quantity())) {
			throw new ArithmeticException("You can't divide by 0!");
		}
		if (i.compareTo(new Quantity()) < 0) {
			positif = !positif;
		}
		i.positif = true;
		Quantity rest = new Quantity();

		for (int j = q.size() - 1; j >= 0; j--) {
			rest.addOrSub(q.get(j));

			int a = 0;

			while (rest.compareTo(i) > -1) {
				a++;
				i.positif = false;
				rest.addOrSub(i);
				i.positif = true;
			}

			if (a > 0)
				q.set(j, a);
			else
				q.remove(j);

			if (j > 0)
				rest.mult(1000);
		}
	}

	public void div(int i) {
		if (i == 0) {
			throw new ArithmeticException("You can't divide by 0!");
		}
		if (i < 0) {
			positif = !positif;
			i *= -1;
		}

		int rest = 0;

		for (int j = q.size() - 1; j >= 0; j--) {

			int t = (rest * 1000) + q.get(j);
			int a = t / i;

			if (a > 0)
				q.set(j, a);
			else
				q.remove(j);

			rest = t - (a * i);
		}
		update();
	}

	public void mult(Quantity i) {
		if (i.equals(new Quantity())) {
			q = new ArrayList<>();
			q.add(0);
			positif = true;
			return;
		}
		if (i.compareTo(new Quantity()) < 0) {
			positif = !positif;
			i.positif = true;
		}
		Quantity a = new Quantity();
		a.addOrSub(1);
		Quantity t = new Quantity(this);
		for (; i.compareTo(a) > 0; i.addOrSub(-1)) {
			addOrSub(t);
		}
	}

	public void mult(int i) {
		if (i == 0) {
			q = new ArrayList<>();
			q.add(0);
			positif = true;
			return;
		}
		if (i < 0) {
			positif = !positif;
			i *= -1;
		}
		Quantity t = new Quantity(this);
		for (; i > 1; i--) {
			addOrSub(t);
		}
	}

	public void addOrSub(Quantity o) {
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

	public void addOrSub(int i) {
		if (i == 0) {
			return;
		}

		if (!positif)
			i *= -1;

		if (i > 0)
			add(i);
		else
			remove(-1 * i);

		update();
	}

	private void add(int i) {
		int l = 0;
		while (i > 0) {
			if (q.size() <= l) {
				for (int t = q.size(); t <= l; t++) {
					q.add(0);
				}
			}
			q.set(l, (int) q.get(l) + (i % 1000));
			i /= 1000;
			l++;
		}
	}

	private void remove(int i) {
		int l = 0;
		while (i > 0) {
			if (q.size() <= l) {
				for (int t = q.size(); t < l; t++) {
					q.set(t, 0);
				}
			}
			q.set(l, (int) q.get(l) - (i % 1000));
			i /= 1000;
			l++;
		}
	}

	private void update() {
		for (int i = 0; i < q.size(); i++) {
			if (q.get(i) < 0) {
				if (q.size() >= i + 1) {
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
	}

	@Override
	public int compareTo(Quantity o) {

		if (positif && !o.positif)
			return 1;
		if (!positif && o.positif)
			return -1;

		if (q.size() > o.q.size())
			return 1;
		if (q.size() < o.q.size())
			return -1;

		for (int i = q.size() - 1; i >= 0; i--) {
			if (q.get(i) > o.q.get(i))
				return 1;
			if (q.get(i) < o.q.get(i))
				return -1;
		}
		return 0;
	}

	public int compareTo(int o) {

		if (positif && o < 0)
			return 1;
		if (!positif && o > 0)
			return -1;

		if (compareTo(new Quantity(String.valueOf(Integer.MAX_VALUE))) > 0)
			return 1;

		return compareTo(new Quantity(String.valueOf(o)));
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Quantity) {
			return compareTo((Quantity) o) == 0;
		}
		return false;
	}

	public String fullString() {
		String s = "";
		if (!positif)
			s += "-";
		for (int i = q.size() - 1; i >= 0; i--) {
			String t = String.valueOf(q.get(i));
			if (i != q.size() - 1)
				while (t.length() < 3) {
					t = "0" + t;
				}
			s += t;
		}
		return s;
	}

	@Override
	public String toString() {
		if (q.size() == 0)
			return "0";

		if (q.size() == 1)
			return (positif ? "" : "-") + q.get(0);

		List<Character> s = new ArrayList<Character>();
		s.add((char) 96);
		int size = q.size();
		while (size > 1) {
			int i = 0;
			while (true) {
				s.set(i, (char) (s.get(i) + 1));
				if (s.get(i) > 122) {
					s.set(i, (char) 97);
					s.add((char) 96);
					i++;
				} else {
					break;
				}
			}
			size--;
		}
		String h = "";
		for (int i = 0; i < s.size(); i++) {
			h = s.get(i) + h;
		}

		String t = String.valueOf(q.get(q.size() - 2));
		if (t != "0")
			while (t.length() < 3) {
				t = "0" + t;
			}
		return (positif ? "" : "-") + q.get(q.size() - 1) + "," + t + h;
	}

	@Override
	public int intValue() {
		return Integer.valueOf(fullString());
	}

	@Override
	public long longValue() {
		return Long.valueOf(fullString());
	}

	@Override
	public float floatValue() {
		return Float.valueOf(fullString());
	}

	@Override
	public double doubleValue() {
		return Double.valueOf(fullString());
	}
}
