package com.programacion.lista;

public class Principal {

	public static void main(String[] args) {

		// Deber
		// Hacer Right-To-Left y Left-To-Right de Map

		System.out.println("Deber");

		Lista<Integer> l1 = new Lista<>(1, 2, 3, 4, 5, 6);
		System.out.println("Lista original: ");
		l1.forEach(System.out::println);

		// Right-To-Left
		System.out.println("Right-To-Left");
		Lista<String> listaRTL = l1.mapFoldRight(x -> "valor " + x);
		listaRTL.forEach(System.out::println);

		System.out.println("Left-To-Right");
		// Left-To-Right
		Lista<String> listaLTR = l1.mapFolfLeft(x -> "valor " + x);
		listaLTR.forEach(System.out::println);

	}

}
