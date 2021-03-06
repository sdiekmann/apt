/*-
 * APT - Analysis of Petri Nets and labeled Transition systems
 * Copyright (C) 2014-2015  Uli Schlachter
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package uniol.apt.analysis.synthesize.separation;

import uniol.apt.analysis.synthesize.PNProperties;
import uniol.apt.analysis.synthesize.RegionUtility;

import org.testng.annotations.Factory;

/** @author Uli Schlachter */
public class MarkedGraphSeparationTest {
	static public class MarkedGraphSeparationFactory implements SeparationTestHelper.SeparationFactory {
		@Override
		public Separation createSeparation(RegionUtility utility, String[] locationMap) {
			try {
				return new MarkedGraphSeparation(utility, new PNProperties(), locationMap);
			} catch (UnsupportedPNPropertiesException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public boolean supportsImpure() {
			return true;
		}

		@Override
		public Separation createSeparation(RegionUtility utility, PNProperties properties,
				String[] locationMap) throws UnsupportedPNPropertiesException {
			return new MarkedGraphSeparation(utility, properties, locationMap);
		}
	}

	@Factory
	public Object[] factory() {
		return SeparationTestHelper.factory(new MarkedGraphSeparationFactory(), false, false);
	}
}

// vim: ft=java:noet:sw=8:sts=8:ts=8:tw=120
