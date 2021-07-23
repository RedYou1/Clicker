package ca.RedYou.Game;

import java.util.ArrayList;
import java.util.List;

public class Quantity implements Comparable<Quantity> {

	private String s;
	public int div;
	public boolean positif;
	public final static String poss = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,<>/?;:\'\"[{]}\\|¦~!@#$%^&*()`+±=_§©®µ¶¤";

	public Quantity() {
		s = String.valueOf(poss.charAt(0));
		div = 0;
		positif = true;
	}

	public Quantity(Quantity q) {
		s = q.s;
		div = q.div;
		positif = q.positif;
	}

	public void mod(Quantity i) {
		i = new Quantity(i);
		Quantity comp = new Quantity();
		if (i.equals(comp))
			throw new ArithmeticException("can't divide by zero");

		int compToi = compareTo(i);
		positif = positif == i.positif;
		if (compToi < 0) {
			return;
		}
		if (compToi == 0) {
			s = "0";
			div = 0;
			return;
		}

		for (int var = 0; compareTo(i) > -1 && i.s.length() + var <= s.length();) {
			Quantity a = valueOf(s.substring(0, i.s.length() + var));
			int time = 0;
			while (a.compareTo(i) > -1) {
				a.sub(i);
				time++;
			}
			if (time > 0) {
				int restant = s.length() - (i.s.length() + var);

				if (a.s.equalsIgnoreCase("0")) {
					s = s.substring(i.s.length() + var, s.length());
				} else {
					s = a.s + s.substring(i.s.length() + var, s.length());
				}

				if (restant > 0 && compareTo(i) == -1) {
					while (restant > 0)
						restant--;
					break;
				}
				var = 0;
			} else {
				var++;
			}
		}

		update();
	}

	public void div(Quantity i) {
		i = new Quantity(i);
		Quantity comp = new Quantity();
		if (i.equals(comp))
			throw new ArithmeticException("can't divide by zero");

		if (equals(comp))
			return;

		if (i.equals(valueOf(1)))
			return;

		boolean positif = this.positif == i.positif;
		int tdiv = div - i.div;
		div = 0;
		i.div = 0;
		positif = i.positif;
		if (compareTo(i) == 0) {
			s = "1";
			div = 0;
			return;
		}

		String h = "";
		for (int var = 0; !equals(comp);) {
			Quantity a = valueOf(s.substring(0, Math.min(s.length(), i.s.length() + var)));
			while (a.compareTo(i) == -1) {
				a.s += "0";
				tdiv++;
			}
			int time = 0;
			while (a.compareTo(i) > -1) {
				a.sub(i);
				time++;
			}
			if (time > 0) {
				h += valueOf(time).s;

				if (a.s.equalsIgnoreCase("0")) {
					s = s.substring(i.s.length() + var, s.length());
				} else {
					s = a.s + s.substring(i.s.length() + var, s.length());
				}

				var = 0;
			} else {
				h += "0";
				var++;
			}
		}

		s = h;
		div = tdiv;
		this.positif = positif;
		update();
	}

	public void mult(Quantity i) {
		i = new Quantity(i);
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
			t.s += "0";
		}

		s = t.s.substring(0, t.s.length() - 1);

		div += i.div;

		update();
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

		while (div > i.div) {
			i.s += "0";
			i.div++;
		}

		while (div < i.div) {
			s += "0";
			div++;
		}

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

		update();
	}

	public void add(Quantity i) {
		i = new Quantity(i);
		if (positif != i.positif) {
			i.positif = positif;
			sub(i);
			return;
		}

		while (div > i.div) {
			i.s += "0";
			i.div++;
		}

		while (div < i.div) {
			s += "0";
			div++;
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

		update();
	}

	/**
	 * 
	 * @param space <br>
	 *              0 = unite <br>
	 *              >0 = left of the unite <br>
	 *                   (ex:1=10<br>
	 *                        2=100<br>
	 *                        3=1000) <br>
	 *              <0 = right of the unite <br>
	 *                   (ex:-1=0.1<br>
	 *                        -2=0.01<br>
	 *                        -3=0.001)
	 */
	public void round(int space) {
		if (div < space && space < 0)
			return;

		floor(space - 1);
		int i = s.length() - 1;

		if (s.charAt(i) != '0' && i > 0) {
			if (space % 2 == 0) {
				int a = poss.indexOf(s.charAt(i));
				if (a >= 50) {
					while (true) {
						int l = poss.indexOf(s.charAt(i - 1));
						l++;
						if (l < poss.length()) {
							s = s.substring(0, i - 1) + poss.charAt(l) + s.substring(i, s.length());
							break;
						}
						s = s.substring(0, i - 1) + "0" + s.substring(i, s.length());
						i--;
					}
				}

				s = s.substring(0, i) + "0" + s.substring(i + 1, s.length());
			} else {
				int a = poss.indexOf(s.charAt(i));
				if (a % 10 >= 5) {
					if (a > 90) {
						round(space + 1);
						return;
					} else {
						s = s.substring(0, i) + poss.charAt(((a / 10) + 1) * 10) + s.substring(i + 1, s.length());
					}
				}
			}
		}
		floor(space);
	}

	/**
	 * 
	 * @param space <br>
	 *              0 = unite <br>
	 *              >0 = left of the unite <br>
	 *                   (ex:1=10<br>
	 *                        2=100<br>
	 *                        3=1000) <br>
	 *              <0 = right of the unite <br>
	 *                   (ex:-1=0.1<br>
	 *                        -2=0.01<br>
	 *                        -3=0.001)
	 */
	public void floor(int space) {
		if (div < space && space < 0)
			return;

		if (space == 0) {
			s = s.substring(0, s.length() - div);
			div = 0;
			return;
		}
		if (space % 2 == 0) {
			space /= 2;
			if (space > 0) {
				s = s.substring(0, s.length() - space);
				while (space > 0) {
					s += "0";
					space--;
				}
				return;
			} else if (div > 0) {
				s = s.substring(0, s.length() - space - div);
				div = -space;
				return;
			}
		} else {
			floor(space - 1);
			update();
			int i = poss.indexOf(s.charAt(s.length() - 1));
			i = (i / 10) * 10;
			s = s.substring(0, s.length() - 1) + poss.charAt(i);
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

		if (s.contains(".")) {
			int a = s.indexOf('.');
			s = s.replace(".", "");
			q.div = s.length() - a;

			while (s.startsWith("0")) {
				s = s.substring(1, s.length());
			}
		}

		q.s = s;
		q.update();
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
		q.update();
		return q;
	}

	public static Quantity valueOf(float i) {
		return valueOf((double) i);
	}

	public static Quantity valueOf(double i) {
		Quantity q = new Quantity();
		q.s = "";
		if (i == 0) {
			q.s = String.valueOf(poss.charAt(0));
		}

		if (i < 0) {
			i *= -1;
			q.positif = false;
		}

		String a = String.valueOf(i - Math.floor(i));
		long l = (long) i;
		if (!a.equalsIgnoreCase("0")) {
			q.div = (int) Math.min(3, Math.ceil(Math.pow(10, a.length() - 2) / 2));
			l = Math.round(i * Math.pow(10, q.div * 2));
		}
		while (l > 0) {
			q.s = poss.charAt((int) (l % poss.length())) + q.s;
			l /= poss.length();
		}
		q.update();
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
		update();
		o.update();

		if (positif && !o.positif)
			return 1;
		if (!positif && o.positif)
			return -1;

		if (s.length() - div > o.s.length() - o.div)
			return 1;
		if (s.length() - div < o.s.length() - o.div)
			return -1;

		for (int i = 0; i < s.length() && i < o.s.length(); i++) {
			int a = poss.indexOf(s.charAt(i));
			int b = poss.indexOf(o.s.charAt(i));
			if (a > b)
				return 1;
			if (a < b)
				return -1;
		}

		if (div > o.div)
			return 1;
		if (div < o.div)
			return -1;

		return 0;
	}

	public long toLong() {
		update();
		long l = 0;
		for (int i = 0; i < s.length() - div; i++) {
			l += convert(s.charAt(i), s.length() - i - 1 - div);
		}
		return l;
	}

	public double toDouble() {
		update();
		double l = 0;
		for (int i = 0; i < s.length(); i++) {
			l += convert(s.charAt(i), s.length() - i - 1 - div);
		}
		return l;
	}

	private static double convert(char c, int index) {
		return poss.indexOf(c) * Math.pow(poss.length(), index);
	}

	private void update() {
		if (s.length() == 0)
			s = "0";
		if (s.equals("0"))
			positif = true;
		while (s.startsWith("0") && s.length() - div > 1) {
			s = s.substring(1, s.length());
		}
		while (!s.equalsIgnoreCase("0") && s.endsWith("0") && div > 0) {
			s = s.substring(0, s.length() - 1);
			div--;
		}
	}

	public String extract() {
		update();
		if (div == 0)
			return (positif ? "" : "-") + s;
		else {
			String s = this.s;
			while (div + 1 > s.length()) {
				s = "0" + s;
			}
			return (positif ? "" : "-") + s.substring(0, s.length() - div) + "."
					+ s.substring(s.length() - div, s.length());
		}
	}

	@Override
	public String toString() {
		update();
		String t = "";

		for (int i = 0; i < s.length() && t.length() < 4; i++) {
			t += s.charAt(i);
		}

		int size = (s.length() - div) * 2;

		if (!Character.isDigit(t.charAt(0)))
			size++;

		size++;
		int temp = size % 3 + 1;
		size /= 3;
		size--;

		long lt = valueOf(t).toLong();

		String a = String.valueOf(lt / (Math.pow(100, div)));

		if (size > 0)
			a = a.substring(0, temp) + "," + a.substring(temp, Math.min(a.length(), temp + 3));

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
