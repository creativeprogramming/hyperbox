/*
 * Hyperbox - Virtual Infrastructure Manager
 * Copyright (C) 2013 Maxime Dor
 * 
 * http://kamax.io/hbox/
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

package org.altherian.hboxc.front.gui.store;

import org.altherian.hbox.comm.out.StoreOut;
import org.altherian.hboxc.front.gui.utils.AbstractOutputListTableModel;

@SuppressWarnings("serial")
public final class StoreListTableModel extends AbstractOutputListTableModel<StoreOut> {

   private final String ID = "ID";
   private final String LABEL = "Label";
   private final String LOC = "Location";

   @Override
   protected void addColumns() {
      addColumn(ID);
      addColumn(LABEL);
      addColumn(LOC);
   }

   @Override
   protected Object getValueAt(StoreOut obj, String columnName) {
      if (ID.equalsIgnoreCase(columnName)) {
         return obj.getId();
      }
      if (LABEL.equalsIgnoreCase(columnName)) {
         return obj.getLabel();
      }
      if (LOC.equalsIgnoreCase(columnName)) {
         return obj.getLocation();
      }
      return null;
   }

}
