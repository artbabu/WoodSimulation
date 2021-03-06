/* 
 * Copyright (c) 2011/13 Martin Prout
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * http://creativecommons.org/licenses/LGPL/2.1/
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package lsystem.collection;

import java.util.HashMap;
import java.util.Map;
import lsystem.collection.csrule.ContextRule;

/**
 * A storage class for context sensitive LSystem rules, accepts simple LSystem
 * rules as well (however as yet stochastic rules are not accepted).
 * The expected format for context is 'x>z' or 'x<z' where x is the context char.
 * '<' is before and '>' is after z is the premis or character to be replaced.
 * There is ability to ignore certain characters (eg []0-9) when determining context.
 * @author Martin Prout
 */
public class CSList {

    private char[] ignore;
    private Map<Character, ContextRule> cs_premises;
    private Map<Character, String> rules;
    private Map<String, String> csrules;

    /**
     *
     */
    public CSList() {
        cs_premises = new HashMap<Character, ContextRule>();
        rules = new HashMap<Character, String>();
        csrules = new HashMap<String, String>();
        ignore = new char[0]; 
    }

    /**
     * Set the character ignore list
     * @param str ignore list as a String
     */
    public void setIgnoreList(String str) {
        this.ignore = str.toCharArray();
    }

    /**
     * Set the character ignore list
     * @param ignore list as char[]
     */
    public void setIgnoreList(char[] ignore) {
        System.arraycopy(ignore, 0, this.ignore, 0, ignore.length);
    }

    /**
     * Check the character ignore list
     * @param prod char
     * @return boolean true when prod in list
     */
    public boolean isIgnored(char prod) {
        boolean isIgnore = false;
        if (ignore.length > 0) {
            for (char c : ignore) {
                if (c == prod) {
                    isIgnore = true;
                    break; // then don't check any more
                }
            }
        }
        return isIgnore;
    }

    /**
     *
     * @param pre
     * @param rule
     * @throws RuntimeException
     */
    public void addRule(String pre, String rule) throws RuntimeException {
        ContextRule context = new ContextRule(pre);
        cs_premises.put(context.getPremis(), context);
        csrules.put(pre, rule);
    }

    /**
     *
     * @param pre
     * @param rule
     * @throws RuntimeException
     */
    public void addRule(char pre, String rule) throws RuntimeException {
        rules.put(pre, rule);
    }

    /**
     *
     * @param pre
     * @param rule
     * @param weight
     * @throws RuntimeException
     */
    public void addRule(char pre, String rule, float weight) throws RuntimeException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * This method controls access to the substitition rules, looks first for a context sensitive rule
     * returns that if context condition is met, else returns non context sensitive rule or prefix
     * @param pre char premis
     * @param production String
     * @param index int
     * @return StringBuilder rule
     */
    public StringBuilder getCSRule(char pre, String production, int index) {  
        StringBuilder rule = new StringBuilder();       
        if (cs_premises.containsKey(pre)) {
            ContextRule context = cs_premises.get(pre);            
            int idx = context.getIndex();
            // guard against index out of bounds, and scroll around context
            if ((idx == -1)&&(index == 0)){index = production.length();}
            if ((idx == 1)&&(index == production.length()-1)){index = -1;}
            char contextChar = context.getContextChar();
            int count = index + idx;
                while (isIgnored(production.charAt(count))) {
                      count += idx;
                } 
                if (contextChar == production.charAt(count)) { 
                    rule.append(csrules.get(context.getKeyHash()));
                } else if (rules.containsKey(pre)) {
                    rule.append(rules.get(pre));
                } else // this is a kind of failsafe since using
                {      // classes should use hasRule as a filter
                    rule.append(pre);   
                }   
        }
        
        else if (rules.containsKey(pre)) {
            rule.append(rules.get(pre));
        } else // this is a kind of failsafe since using
        {
            rule.append(pre);        // classes should use hasRule as a filter
        }        // classes should use hasRule as a filter
        return rule;
    }

    /**
     *
     * @param pre
     * @return
     */
    public boolean hasRule(char pre) {
        boolean has = (rules.containsKey(pre)) || (cs_premises.containsKey(pre));
        return has;
    }

    /**
     * 
     */
    public void clear() {
        rules.clear();
        csrules.clear();
        cs_premises.clear();
    }

    /**
     *
     * @return formatted StringBuilder instance with cs rules
     */
    public StringBuilder toStringBuilder() {
        StringBuilder builder = new StringBuilder();
        if (ignore.length > 0){
            
            builder.append("Ignore:\n\t");
            for (char ign : ignore){
                builder.append(ign);
            }
            builder.append('\n');
        }
        builder.append("Rules:\n");
        for (Character premis : rules.keySet()){
            builder.append('\t');
            builder.append(premis);
            builder.append("   => ");
            builder.append(rules.get(premis));
            builder.append('\n');
        }
        for (String premis : csrules.keySet()){
            builder.append('\t');
            builder.append(premis);
            builder.append(" => ");
            builder.append(csrules.get(premis));
            builder.append('\n');
        }
        return builder;
    }
}
