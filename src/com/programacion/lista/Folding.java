package com.programacion.lista;

import java.util.List;
import java.util.function.Function;

public class Folding {
	//LTR
	// List<T> List<Integer>
	// fn: UxT->U
	// vi en U identidad=0.0
	public static <T, U> U foldLeft(List<T> list, U identidad, Function<U, Function<T, U>> fn) {
		U res = identidad;

		for (T t : list) {

			res = fn.apply(res).apply(t); // vi=fn(t,u)
		}
		return res;

	}
	//RTL
	// List<T> List<Integer>
	// fn: TxU->U Function<Integer,Function<Double,Double>>
	// vi en U identidad=0.0
	public static <T, U> U foldRight(List<T> list, U identidad, Function<T, Function<U, U>> fn) {
		U res = identidad;

		for (int i=list.size()-1;i>=0;i--) {

			T elem= list.get(i);
			res = fn.apply(elem).apply(res); 
		}
		return res;

	}

	// --Folding LTR
	public static void main(String[] args) {

		// sumar los elementos
		List<Integer> list = List.of(1, 2, 3, 4, 5, 6);

		Double res = foldLeft(list, 0.0, x -> y -> x + y);
		System.out.println(res);
//		Function<Double, Function<Integer, Double>> fn =x->y->x+y;
//		Double identidad=0.0;//valor inicial ,tipo U
//		
//		for (Integer t : list) {
//			
//			identidad= fn.apply(identidad).apply(t); //vi=fn(t,u)
//		}
//		System.out.println(identidad);

		// concatenar
		
		String res2 = foldLeft(list, "0", s -> x -> String.format("(%s + %d)", s, x));
		System.out.println(res2);
		//---------
		Function<Integer, Function<String, String>> fn = x -> s -> String.format("(%d + %s)", x, s);
		String res3 = foldRight(list, "0", fn);
		System.out.println(res3);
	}
}
