package com.chong.ulti.test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.chong.entity.wrapper.Sites;
import com.chong.site.Site;
import com.chong.ulti.FieldOperations;

public class FieldOperationsTest {
	private static FieldOperations<Site> fo_site;
	private static FieldOperations<Sites> fo_sites;
	
	@BeforeClass
	public static void beforeClass() {
		fo_site = new FieldOperations<Site>(new Site());
		fo_sites = new FieldOperations<Sites>(new Sites());
	}
	
	@Test
	public void testMatchConstraint() {
		Site site = new Site();
		site.setId((short) 10);
		site.setLocation("China");
		site.setName("Cogent");
		
		assertThat(fo_site.matchConstraint(site, "name", "Cogent"), is(true));
		assertThat(fo_site.matchConstraint(site, "id", (short) 10), is(true));
		assertThat(fo_site.matchConstraint(site, "location", "China"), is(true));
		assertThat(fo_site.matchConstraint(site, "FOO", "FOO"), is(false));
		assertThat(fo_site.matchConstraint(site, "name", "Cogent123"), is(false));
		assertThat(fo_site.matchConstraint(site, "id", (short) 11), is(false));
		assertThat(fo_site.matchConstraint(site, "location", "Pitts"), is(false));
	}

	@Test
	public void testGetFieldValue() {
		Site site = new Site();
		site.setId((short) 10);
		site.setLocation("China");
		site.setName("Cogent");
		
		assertThat((String)fo_site.getFieldValue(site, "name"), is("Cogent"));
		assertThat((String)fo_site.getFieldValue(site, "location"), is("China"));
		assertThat((int) ((Short)fo_site.getFieldValue(site, "id")), equalTo(10));
		assertThat((Site.Hosts)fo_site.getFieldValue(site, "hosts"), is(nullValue()));
		// test wrong field
		assertThat((String)fo_site.getFieldValue(site, "FOO"), is(nullValue()));
	}

	@Test 
	public void testHaveField() {
		
		// test fields of "Site"
		assertThat(fo_site.haveField("name"), is(true));
		assertThat(fo_site.haveField("hosts"), is(true));
		assertThat(fo_site.haveField("id"), is(true));
		assertThat(fo_site.haveField("location"), is(true));
		
		// test fields of "Sites"
		assertThat(fo_sites.haveField("sites"), is(true));
		
		// test wrong field
		assertThat(fo_site.haveField("FOO"), is(false));
	}

}
