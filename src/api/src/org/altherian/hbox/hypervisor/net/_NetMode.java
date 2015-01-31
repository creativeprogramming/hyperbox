/*
 * Hyperbox - Enterprise Virtualization Manager
 * Copyright (C) 2015 Maxime Dor
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.altherian.hbox.hypervisor.net;

import java.util.Set;

public interface _NetMode {
   
   public String getId();
   
   public String getLabel();
   
   public Set<String> getSupportedServices();
   
   /**
    * Can this network mode support adaptor?
    *
    * @return true if yes, false if not
    */
   public boolean canLinkAdaptor();
   
   /**
    * Can we add a new adaptor for this network mode?
    *
    * @return true if yes, false if not
    */
   public boolean canAddAdaptor();
   
   /**
    * Can we remove an existing adaptor for this network mode?
    *
    * @return true if yes, false if not
    */
   public boolean canRemoveAdaptor();
   
   /**
    * Can this network mode support arbitrary network name?
    *
    * @return true if yes, false if not
    */
   public boolean canLinkNetworkName();
   
}