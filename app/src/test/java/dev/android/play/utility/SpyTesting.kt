package dev.android.play.utility

import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Spy


class SpyTesting {

    @Spy
    var spyList: MutableList<String> = ArrayList()

    @Test
    fun whenUsingTheSpyAnnotation_thenObjectIsSpied() {
        spyList.add("one")
        spyList.add("two")
        Mockito.verify(spyList).add("one")
        Mockito.verify(spyList).add("two")
        assertTrue(spyList.size == 2)
    }

}


