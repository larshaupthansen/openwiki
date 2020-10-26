package openwiki.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@SpringBootConfiguration()

class PageTests {

	@Test
	 void absoluteUriFrontpage() {



		Page p = new Page() {};
		p.setUri("");
		p.setParentId(0);
		p.setParent(null);

		assertEquals("/", p.getAbsoluteUri());
	}
	@Test
	void absoluteUriFirstLevel() {

		Page p = new Page() {};
		p.setUri("");
		p.setParentId(0);
		p.setParent(null);

		Page p2= new Page() {};
		p2.setUri("some-uri");
		p2.setParentId(1);
		p2.setParent(p);

		assertEquals("/some-uri", p2.getAbsoluteUri());
	}

	@Test
	void absoluteUriSecondLevel() {

		Page p = new Page() {};
		p.setUri("");
		p.setParentId(0);
		p.setParent(null);

		Page p2= new Page() {};
		p2.setUri("some-uri");
		p2.setParentId(1);
		p2.setParent(p);

		Page p3= new Page() {};
		p3.setUri("foo");
		p3.setParentId(2);
		p3.setParent(p2);

		assertEquals("/some-uri/foo", p3.getAbsoluteUri());
	}

}
