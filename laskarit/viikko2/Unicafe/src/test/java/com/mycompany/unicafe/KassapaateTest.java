
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate paate;
    
    @Before
    public void setUp() {
        this.paate = new Kassapaate();
    }
    
    @Test
    public void kassassaOikeaMaaraRahaaAluksi() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void maukkaitaMyytyNollaAluksi() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisiaMyytyNollaAluksi() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void tasarahaSyoEdullisestiToimii() {
        assertEquals(0, paate.syoEdullisesti(240));
    }
    
    @Test
    public void tasarahaSyoMaukkaastiToimii() {
        assertEquals(0, paate.syoMaukkaasti(400));
    }
    
    @Test
    public void kassaKasvaaEdullisesti() {
        paate.syoEdullisesti(240);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void kassaKasvaaMaukkaasti() {
        paate.syoMaukkaasti(400);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void oikeaVaihtorahaEdullisesti() {
        assertEquals(60, paate.syoEdullisesti(300));
    }
    
    @Test
    public void oikeaVaihtorahaMaukkaasti() {
        assertEquals(60, paate.syoMaukkaasti(460));
    }
    
    @Test
    public void myydytLounaatKasvaaEdullisesti() {
        paate.syoEdullisesti(240);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myydytLounaatKasvaaMaukkaasti() {
        paate.syoMaukkaasti(400);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaEiMuutuJosEiRahaa() {
        paate.syoEdullisesti(200);
        paate.syoMaukkaasti(300);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void rahatPalautetaanJosEiRahaaEdullisesti() {
        assertEquals(200, paate.syoEdullisesti(200));
    }
    
    @Test
    public void rahatPalautetaanJosEiRahaaMaukkaasti() {
        assertEquals(200, paate.syoMaukkaasti(200));
    }
    
    @Test
    public void myydytLounaatEiMuutuJosEiRahaaEdullisesti() {
        paate.syoEdullisesti(200);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myydytLounaatEiMuutuJosEiRahaaMaukkaasti() {
        paate.syoMaukkaasti(200);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortiltaVeloitetaanJosRahaa() {
        Maksukortti kortti = new Maksukortti(640);
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void palauttaaTrueJosKorttimaksuOnnistuuEdullisesti() {
        Maksukortti kortti = new Maksukortti(240);
        assertTrue(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void palauttaaTrueJosKorttimaksuOnnistuuMaukkaasti() {
        Maksukortti kortti = new Maksukortti(400);
        assertTrue(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void myydytLounaatKasvaaKortillaEdulllisesti() {
        Maksukortti kortti = new Maksukortti(240);
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myydytLounaatKasvaaKortillaMaukkaasti() {
        Maksukortti kortti = new Maksukortti(400);
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortiltaEiVeloitetaJosEiRahaa() {
        Maksukortti kortti = new Maksukortti(200);
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void palauttaaFalseJosKorttimaksuEiOnnistuEdullisesti() {
        Maksukortti kortti = new Maksukortti(200);
        assertFalse(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void palauttaaFalseJosKorttimaksuEiOnnistuMaukkaasti() {
        Maksukortti kortti = new Maksukortti(200);
        assertFalse(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void myydytLounaatEiKasvaKortillaJosEiRahaaEdulllisesti() {
        Maksukortti kortti = new Maksukortti(200);
        paate.syoEdullisesti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myydytLounaatEiKasvaKortillaJosEiRahaaMaukkaasti() {
        Maksukortti kortti = new Maksukortti(200);
        paate.syoMaukkaasti(kortti);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaEiMuutuKortilla() {
        Maksukortti kortti = new Maksukortti(640);
        paate.syoMaukkaasti(kortti);
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        Maksukortti kortti = new Maksukortti(0);
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void kortilleLadattuTuleeKassaan() {
        Maksukortti kortti = new Maksukortti(0);
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, paate.kassassaRahaa());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        Maksukortti kortti = new Maksukortti(0);
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void kortilleLadattuNegatiivinenEiNayKassassa() {
        Maksukortti kortti = new Maksukortti(0);
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, paate.kassassaRahaa());
    }
}
