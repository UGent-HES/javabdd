// Copyright (C) 2014 Karel Heyse
package regression;

import junit.framework.Assert;
import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;
import bdd.BDDTestCase;

/**
 * compose() bug
 * 
 * @author Karel Heyse
 * @version $Id$
 */
public class R4 extends BDDTestCase {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(R4.class);
    }
    
    public void testR4() {
        Assert.assertTrue(hasNext());
        while (hasNext()) {
            
            BDDFactory bdd = nextFactory();
            BDD x0,x1,x2,y0,y1,z0,z1;
            bdd.setVarNum(5);
            
            x0 = bdd.ithVar(0);
            x1 = bdd.ithVar(1);
            x2 = bdd.ithVar(2);

            y0 = x0.or(x1);
            y1 = x0.or(x2);

            z0 = y0.compose(x2, x1.var());
            Assert.assertEquals(y1, z0);
            z1 = y0.veccompose(bdd.makePair(x1.var(), x2));
            Assert.assertEquals(y1, z1);
            x0.free(); x1.free(); x2.free(); y0.free(); y1.free(); z0.free(); z1.free();
            
        }
    }
}
