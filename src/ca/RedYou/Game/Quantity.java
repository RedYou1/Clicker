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

	public void add(Quantity i) {
		int t = 0;
		long last = 0;
		for (; t < s.length() && t < i.s.length(); t++) {
			long a = convert(s.charAt(s.length() - 1 - t), 0);
			long b = convert(i.s.charAt(i.s.length() - 1 - t), 0);

			String d = valueOf(a + b + last).extract();

			s = s.substring(0, s.length() - t - 1) + d.charAt(d.length() - 1) + s.substring(s.length() - t, s.length());
			last = valueOf(d.substring(0, d.length() - 1)).toLong();
		}

		if (last > 0) {
			s = valueOf(last).extract() + s;
		}
		if (t < i.s.length()) {
			s = i.s.substring(0, i.s.length() - t) + s;
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
		return (positif ? "" : "-") + s;
	}

	@Override
	public String toString() {
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
