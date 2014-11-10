/*-
 * APT - Analysis of Petri Nets and labeled Transition systems
 * Copyright (C) 2012-2014  Members of the project group APT
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

package uniol.apt.synthesize;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Matcher to verify that a region satisfies some constraints.
 * @author Uli Schlachter, vsp
 */
public class PureRegionWithWeightsThatMatcher extends TypeSafeDiagnosingMatcher<Region> {

	private final String event;
	private final Matcher<Integer> weightWatcher;

	private PureRegionWithWeightsThatMatcher(String event, Matcher<Integer> weightWatcher) {
		this.event = event;
		this.weightWatcher = weightWatcher;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("region weight('");
		description.appendText(event);
		description.appendText("')=");
		weightWatcher.describeTo(description);
	}

	@Override
	public boolean matchesSafely(Region region, Description description) {
		boolean matches = true;

		if (region.getBackwardWeight(event) != 0 && region.getForwardWeight(event) != 0) {
			description.appendText("region weight('");
			description.appendText(event);
			description.appendText("')=(");
			description.appendText("" + region.getBackwardWeight(event));
			description.appendText(", ");
			description.appendText("" + region.getForwardWeight(event));
			description.appendText(") and thus being impure");
			return false;
		}

		if (!weightWatcher.matches(region.getWeight(event))) {
			description.appendText("region weight('");
			description.appendText(event);
			description.appendText("')=");
			weightWatcher.describeMismatch(region.getWeight(event), description);
			return false;
		}

		description.appendText("}");

		return matches;
	}

	@Factory
	public static Matcher<Region> pureRegionWithWeightsThat(String event, Matcher<Integer> weight) {
		return new PureRegionWithWeightsThatMatcher(event, weight);
	}
}

// vim: ft=java:noet:sw=8:sts=8:ts=8:tw=120
