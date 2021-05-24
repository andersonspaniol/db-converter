/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconverter.params;

/**
 * Any exception occurred during the parsing process
 * 
 * @author Anderson Spaniol
 */
public class ParametersParsingException extends Exception {

    public ParametersParsingException() {
    }

    public ParametersParsingException(String msg, Throwable cause) {
        super(msg);
    }
}
