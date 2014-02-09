/*
 * Hyperbox - Enterprise Virtualization Manager
 * Copyright (C) 2013 Maxime Dor
 * 
 * http://hyperbox.altherian.org
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.altherian.hbox.comm.input;

public final class UserInput extends ObjectInput {
   
   private String domain;
   private String username;
   private char[] password;
   
   public UserInput() {
   }
   
   public UserInput(String id) {
      super(id);
   }
   
   public UserInput(String username, char[] password) {
      this("", username, password);
   }
   
   public UserInput(String domain, String username, char[] password) {
      setDomain(domain);
      setUsername(username);
      setPassword(password);
   }
   
   /**
    * @return the domain
    */
   public String getDomain() {
      return domain;
   }
   
   /**
    * @param domain the domain to set
    */
   public void setDomain(String domain) {
      this.domain = domain;
   }
   
   /**
    * @return the username
    */
   public String getUsername() {
      return username;
   }
   
   /**
    * @param username the username to set
    */
   public void setUsername(String username) {
      this.username = username;
   }
   
   /**
    * @return the password
    */
   public char[] getPassword() {
      return password;
   }
   
   /**
    * @param password the password to set
    */
   public void setPassword(char[] password) {
      this.password = password;
   }
   
}