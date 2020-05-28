/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.med.salemx.controller;

/**
 *
 * @author Carrizal
 */
public class ControllerNormalizar {
    public String normalizar(String txt){
        String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÒÓÔÕÖØÙÚÛÜÝß";
        String asil     = "AAAAAAACEEEEIIIIDOOOOOOUUUUYB";
        String originalM = original.toLowerCase();
        String asilM = asil.toLowerCase();
        for (int i = 0; i < original.length(); i++) {
            txt = txt.replace(original.charAt(i), asil.charAt(i));
        }
        for (int i = 0; i < originalM.length(); i++) {
            txt = txt.replace(originalM.charAt(i), asilM.charAt(i));
        }
        String original2 ="!\"$%&'()*+-/:;<=>?[]^_`{|}°";
        for (int i = 0; i < original2.length(); i++) {
            txt = txt.replace(original2.charAt(i), ' ');
        }
        return txt;
    }
    
    public static void main(String[] args){
        ControllerNormalizar cn = new ControllerNormalizar();
        String txt = "'Róbértó'); DROP * TABLE FROM éstúdíántés;";
        System.out.println("Original : "+ cn.normalizar(txt));
    }
}
