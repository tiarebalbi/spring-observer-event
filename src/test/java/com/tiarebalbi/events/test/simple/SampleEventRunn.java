package com.tiarebalbi.events.test.simple;

import org.springframework.stereotype.Service;

import com.tiarebalbi.events.annotations.Observer;

@Service
public class SampleEventRunn {
	
	public void initObjecter(@Observer SimpleObject simple) {
		System.out.println("Executou comando definido: " + simple.getNome());
	}
	
	public void novoMetodo(@Observer SimpleObject simple) {
		System.out.println("Executou comando definido 2 : " + simple.getNome());
	}

}
