package com.main.constants;
/**
 * Common ENUM
 * @author snavale
 *
 */
public class CommonEnum {

	public enum Clause {

		BETWEEN("between"), ABOVE("above");
		private String label;

		Clause(String label) {
			this.label = label;
		}

		public String getLabel() {
			return this.label;
		}

		public Clause getEnum(String label) {
			for (Clause val : Clause.values()) {
				if (label.equalsIgnoreCase(val.label)) {
					return val;
				}
			}
			return null;

		}

	}

}
