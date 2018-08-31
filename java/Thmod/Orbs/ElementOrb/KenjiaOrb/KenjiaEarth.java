package Thmod.Orbs.ElementOrb.KenjiaOrb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import Thmod.Cards.ElementCards.AbstractElementCards;

public class KenjiaEarth extends AbstractKenjiaOrb {
    public static final String ORB_ID = "KenjiaEarth";
    private static final OrbStrings orbString;
    public static final String NAME;
    public static final String DESCRIPTION[];
    private static Texture img1;

    public KenjiaEarth() {
        this.ID = "KenjiaEarth";
        this.name = KenjiaEarth.NAME;
        this.elementType = AbstractElementCards.ElementType.Earth;
        if (KenjiaEarth.img1 == null) {
            KenjiaEarth.img1 = ImageMaster.loadImage("images/orbs/KenjiaEarth.png");
        }
        this.baseEvokeAmount = 0;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = 0;
        this.passiveAmount = this.basePassiveAmount;
        this.angle = MathUtils.random(360.0f);
        this.cX = AbstractDungeon.player.drawX + AbstractDungeon.player.hb_x;
        this.cY = AbstractDungeon.player.drawY + AbstractDungeon.player.hb_y + AbstractDungeon.player.hb_h / 2.0f;
        this.updateDescription();
//        this.showingEvoke = null;
    }

    public void updateDescription()
    {
        applyFocus();
        this.description = DESCRIPTION[0];
    }

    public void render(final SpriteBatch sb) {
        sb.setColor(this.c);
        sb.draw(KenjiaEarth.img1, this.cX - 48.0f + this.bobEffect.y / 8.0f, this.cY - 48.0f - this.bobEffect.y / 8.0f, 48.0f, 48.0f, 96.0f, 96.0f, this.scale, this.scale, 0.0f, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    protected void renderText(SpriteBatch sb)
    {
    }

    public AbstractOrb makeCopy()
    {
        return new KenjiaEarth();
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("KenjiaEarth");
        NAME = KenjiaEarth.orbString.NAME;
        DESCRIPTION = KenjiaEarth.orbString.DESCRIPTION;
    }
}
