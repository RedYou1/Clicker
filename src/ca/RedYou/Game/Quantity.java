package ca.RedYou.Game;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Quantity implements Comparable<Quantity> {

	private String s;
	public boolean positif;
	public final static String poss = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,<.>/?;:\'\"[{]}\\|~!@#$%^&*()`+±=_§©®µ¶¤";

	public Quantity() {
		s = String.valueOf(poss.charAt(0));
		positif = true;
	}

	public Quantity(Quantity q) {
		s = q.s;
		positif = q.positif;
	}

	/**
	 * 
	 * @param i
	 * @return the mod
	 */
	public Quantity div(Quantity i) {
		Quantity comp = new Quantity();
		if (i.equals(comp))
			throw new ArithmeticException("can't divide by zero");

		int compToi = compareTo(i);
		if (compToi < 0) {
			Quantity t = valueOf(s);
			s = "0";
			positif = true;
			return t;
		}
		if (compToi == 0) {
			s = "1";
			positif = true;
			return valueOf(0);
		}

		positif = positif == i.positif;

		String h = "";
		for (int var = 0; compareTo(i) > -1 && i.s.length() + var <= s.length();) {
			Quantity a = valueOf(s.substring(0, i.s.length() + var));
			int time = 0;
			while (a.compareTo(i) > -1) {
				a.sub(i);
				time++;
			}
			if (time > 0) {
				h += valueOf(time).s;

				int restant = s.length() - (i.s.length() + var);

				if (a.s.equalsIgnoreCase("0")) {
					s = s.substring(i.s.length() + var, s.length());
				} else {
					s = a.s + s.substring(i.s.length() + var, s.length());
				}

				if (restant > 0 && compareTo(i) == -1) {
					while (restant > 0) {
						h += "0";
						restant--;
					}
					break;
				}
				var = 0;
			} else {
				h += "0";
				var++;
			}
		}

		while (h.startsWith("0") && h.length() > 1) {
			h = h.substring(1, h.length());
		}
		Quantity t = valueOf(s);
		s = h;
		return t;
	}

	public void mult(Quantity i) {
		positif = positif == i.positif;

		Quantity t = new Quantity();

		for (int l = 0; l < i.s.length(); l++) {
			int time = poss.indexOf(i.s.charAt(l));
			if (time > 0) {
				Quantity y1 = valueOf(s);
				Quantity y2 = valueOf(s);
				for (; time > 1; time--) {
					y1.add(y2);
				}
				t.add(y1);
			}
		}

		s = t.s;

		while (s.startsWith("0") && s.length() > 1) {
			s = s.substring(1, s.length());
		}
	}

	public void sub(Quantity i) {
		i = new Quantity(i);
		if (positif != i.positif) {
			i.positif = positif;
			add(i);
			return;
		}
		i.positif = !i.positif;

		if (i.s.length() > s.length()) {
			positif = !positif;
			i.sub(this);

			s = i.s;
			positif = i.positif;
			return;
		}

		if (s.equalsIgnoreCase("0"))
			positif = i.positif;

		int t = 0;
		int last = 0;
		for (; t < i.s.length(); t++) {
			long a = poss.indexOf(s.charAt(s.length() - 1 - t));
			long b = poss.indexOf(i.s.charAt(i.s.length() - 1 - t));

			long temp = a - b - last;

			last = 0;
			if (t + 1 != s.length())
				while (temp < 0) {
					temp += poss.length();
					last++;
				}

			String d = valueOf(temp).extract();

			s = s.substring(0, s.length() - t - 1) + d.charAt(d.length() - 1) + s.substring(s.length() - t, s.length());
		}

		for (; t < s.length() && last > 0; t++) {
			long a = poss.indexOf(s.charAt(s.length() - 1 - t));

			long temp = a - last;

			last = 0;
			if (t + 1 != s.length())
				while (temp < 0) {
					temp += poss.length();
					last++;
				}

			String d = valueOf(temp).extract();

			s = s.substring(0, s.length() - t - 1) + d.charAt(d.length() - 1) + s.substring(s.length() - t, s.length());
		}

		if (last > 0) {
			s = poss.charAt(poss.length() - last) + s;
			positif = !positif;
		}

		while (s.startsWith("0") && s.length() > 1) {
			s = s.substring(1, s.length());
		}
	}

	public void add(Quantity i) {
		if (s == "0")
			positif = i.positif;
		if (positif != i.positif) {
			i.positif = positif;
			sub(i);
			return;
		}
		int t = 0;
		long last = 0;
		for (; t < s.length() && t < i.s.length(); t++) {
			long a = poss.indexOf(s.charAt(s.length() - 1 - t));
			long b = poss.indexOf(i.s.charAt(i.s.length() - 1 - t));

			String d = valueOf(a + b + last).extract();

			s = s.substring(0, s.length() - t - 1) + d.charAt(d.length() - 1) + s.substring(s.length() - t, s.length());
			last = valueOf(d.substring(0, d.length() - 1)).toLong();
		}

		while (last > 0 && t < s.length()) {
			long a = poss.indexOf(s.charAt(s.length() - 1 - t));

			String d = valueOf(a + last).extract();

			s = s.substring(0, s.length() - t - 1) + d.charAt(d.length() - 1) + s.substring(s.length() - t, s.length());
			last = valueOf(d.substring(0, d.length() - 1)).toLong();
			t++;
		}

		if (last > 0) {
			s = valueOf(last).extract() + s;
		}
		if (t < i.s.length()) {
			s = i.s.substring(0, i.s.length() - t) + s;
		}

		while (s.startsWith("0") && s.length() > 1) {
			s = s.substring(1, s.length());
		}
	}

	public static Quantity valueOf(String s) {
		if (s.length() == 0)
			return new Quantity();

		Quantity q = new Quantity();

		q.positif = true;
		if (s.startsWith("-")) {
			s = s.replace("-", "");
			q.positif = false;
		}

		q.s = s;

		return q;
	}

	public static Quantity valueOf(int i) {
		return valueOf((long) i);
	}

	public static Quantity valueOf(long i) {
		Quantity q = new Quantity();
		q.s = "";
		if (i == 0) {
			q.s = String.valueOf(poss.charAt(0));
		}

		if (i < 0) {
			i *= -1;
			q.positif = false;
		}

		while (i > 0) {
			q.s = poss.charAt((int) (i % poss.length())) + q.s;
			i /= poss.length();
		}

		return q;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Quantity) {
			return compareTo((Quantity) o) == 0;
		}
		return false;
	}

	@Override
	public int compareTo(Quantity o) {
		if (s.equalsIgnoreCase("0"))
			positif = true;
		if (o.s.equalsIgnoreCase("0"))
			o.positif = true;

		if (positif && !o.positif)
			return 1;
		if (!positif && o.positif)
			return -1;

		if (s.length() > o.s.length())
			return 1;
		if (s.length() < o.s.length())
			return -1;

		for (int i = 0; i < s.length(); i++) {
			int a = poss.indexOf(s.charAt(i));
			int b = poss.indexOf(o.s.charAt(i));
			if (a > b)
				return 1;
			if (a < b)
				return -1;
		}

		return 0;
	}

	public long toLong() {
		long l = 0;
		for (int i = 0; i < s.length(); i++) {
			l += convert(s.charAt(i), s.length() - i - 1);
		}
		return l;
	}

	private static long convert(char c, int index) {
		return poss.indexOf(c) * Math.round(Math.pow(poss.length(), index));
	}

	public String extract() {
		if (s.equals("0"))
			positif = true;
		return (positif ? "" : "-") + s;
	}

	@Override
	public String toString() {
		if (s.equals("0"))
			positif = true;
		String t = "";

		for (int i = 0; i < s.length() && t.length() < 4; i++) {
			t += s.charAt(i);
		}

		int size = s.length() * 2;

		if (!Character.isDigit(t.charAt(0)))
			size++;

		size++;
		int temp = size % 3 + 1;
		size /= 3;
		size--;

		long lt = valueOf(t).toLong();

		String a = String.valueOf(lt);

		if (size > 0)
			a = a.substring(0, temp) + "," + a.substring(temp, temp + 3);

		List<Character> chars = new ArrayList<Character>();
		if (size > 0) {
			chars.add((char) 96);
			do {
				int i = 0;
				while (true) {
					chars.set(i, (char) (chars.get(i) + 1));
					if (chars.get(i) > 122) {
						chars.set(i, (char) 97);
						chars.add((char) 96);
						i++;
					} else {
						break;
					}
				}
				size--;
			} while (size > 0);
		}

		String h = "";
		for (int i = 0; i < chars.size(); i++) {
			h = chars.get(i) + h;
		}
		return (positif ? "" : "-") + a + h;
	}
}
