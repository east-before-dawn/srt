package tools;

import java.util.Arrays;
import java.util.HashSet;

public class Emoticon {
	public static HashSet<String> emoticon = new HashSet<String>(
			Arrays.asList("(谄笑)","(吃饭)","(调皮)","(尴尬)","(汗)","(惊恐)",
			"(囧)","(可爱)","(酷)","(流口水)","(生病)","(叹气)","(淘气)",
			"(舔)","(偷笑)","(吐)","(吻)","(晕)","(住嘴)","(大笑)","(害羞)",
			"(口罩)","(哭)","(困)","(难过)","(生气)","(书呆子)","(微笑)",
			"(不)","(惊讶)","(kb)","(sx)","(ju)","(gl)","(yl)","(hold1)",
			"(cold)","(nuomi)","(石化)","(rs)","(sbq)","(th)","(mb)",
			"(tucao)","(禅师)","(zy)","(by)","(twg)","(good)","(走你)",
			"(小黄鸡)","(lt)","(fz)","(fog)","(scb)","(bs)","(hz)","(mo)",
			"(cap)","(不约而同)","(十动然拒)","(hot)","(feng)","(么么哒)",
			"(sowhat)","(呵呵)","(tk)","(暖)"));
	
	public static boolean contains(String e) {
		if (emoticon.contains(e))
			return true;
		else return false;
	}
}
