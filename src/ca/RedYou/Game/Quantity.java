package ca.RedYou.Game;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Quantity implements Comparable<Quantity> {

	private String s;
	public boolean positif;
	public final static String poss = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,<.>/?;:\'\"[{]}\\|~!@#$%^&*()`+-=_§©®µ¶¤";

	public Quantity() {
		s = String.valueOf(poss.charAt(0));
		positif = true;
	}

	public Quantity(Quantity q) {
		s = q.s;
		positif = q.positif;
	}

	public static Quantity valueOf(String s, boolean base10) {
		if (base10)
			return valueOf(Long.valueOf(s));

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
		Quantity max = valueOf(999);

		for (int i = 0; i < s.length() && valueOf(t, false).compareTo(max) == -1; i++) {
			t += s.charAt(i);
		}

		while (t.length() > 0 && valueOf(t, false).compareTo(max) == 1) {
			t = t.substring(0, t.length() - 1);
		}

		List<Character> chars = new ArrayList<Character>();

		long v = valueOf(t, false).toLong();

		long size = (long) (Math.log10(toLong()) / 3);
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
		return (positif ? "" : "-") + v + h;
	}
}
