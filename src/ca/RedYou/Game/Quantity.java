package ca.RedYou.Game;

import java.util.ArrayList;
import java.util.List;

public class Quantity implements Comparable<Quantity> {

	private List<Integer> q;
	private boolean positif;

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
			q.add(Integer.valueOf(s.substring(i - 2, i)));
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

	public void mult(Quantity i) {
		if (i.equals(new Quantity())) {
			q = new ArrayList<>();
			q.add(0);
			positif = true;
		}
		if (i.compareTo(new Quantity()) < 0) {
			positif = !positif;
			i.positif = true;
		}
		Quantity a = new Quantity();
		a.edit(1);
		Quantity t = new Quantity(this);
		for (; i.compareTo(a) > 0; i.edit(-1)) {
			edit(t);
		}
	}

	public void mult(int i) {
		if (i == 0) {
			q = new ArrayList<>();
			q.add(0);
			positif = true;
		}
		if (i < 0) {
			positif = !positif;
			i *= -1;
		}
		Quantity t = new Quantity(this);
		for (; i > 1; i--) {
			edit(t);
		}
	}

	public void edit(Quantity o) {
		if (positif == o.positif)
			for (int i = 0; i < o.q.size(); i++) {
				int a = q.get(i) + o.q.get(i);
				if (i >= q.size())
					q.add(a);
				else
					q.set(i, a);
			}
		else
			for (int i = 0; i < o.q.size(); i++) {
				int a = q.get(i) - o.q.get(i);
				if (i >= q.size())
					q.add(a);
				else
					q.set(i, a);
			}
		update();
	}

	public void edit(int i) {
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
					return;
				}

				q.set(i, q.get(i) + 1000);
				q.set(i + 1, q.get(i + 1) - 1);
			}

			while (q.get(i) >= 1000) {
				q.set(i, q.get(i) - 1000);
				if (q.size() == i + 1)
					q.add(1);
				else
					q.set(i + 1, q.get(i + 1) + 1);
			}
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
			s += q.get(i);
		}
		return s;
	}

	@Override
	public String toString() {
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
		return (positif ? "" : "-") + q.get(q.size() - 1) + "," + q.get(q.size() - 2) + h;
	}
}
