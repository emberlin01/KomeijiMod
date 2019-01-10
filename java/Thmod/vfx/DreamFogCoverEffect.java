package Thmod.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class DreamFogCoverEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float aV;
    private float targetAlpha = MathUtils.random(0.5F, 0.8F);
    private boolean flipX = MathUtils.randomBoolean();
    private boolean flipY = MathUtils.randomBoolean();
    private TextureAtlas.AtlasRegion img;
    private boolean canDone;
    private boolean firstCannel;


    public DreamFogCoverEffect() {
        this.duration = MathUtils.random(2.0F, 2.5F);
        this.startingDuration = this.duration;
        switch (MathUtils.random(2)) {
            case 0:
                this.img = ImageMaster.SMOKE_1;
                break;
            case 1:
                this.img = ImageMaster.SMOKE_2;
                break;
            default:
                this.img = ImageMaster.SMOKE_3;
        }
        this.x = (MathUtils.random(-100.0F * Settings.scale, Settings.WIDTH + 100.0F * Settings.scale) - this.img.packedWidth / 2.0F);
        this.y = (MathUtils.random(-100.0F * Settings.scale, Settings.HEIGHT + 100.0F * Settings.scale) - this.img.packedHeight / 2.0F);
        this.aV = MathUtils.random(-30.0F, 30.0F);
        this.rotation = MathUtils.random(0.0F, 360.0F);
        float tmp = MathUtils.random(0.8F, 1.0F);
        this.color = new Color();
        this.color.r = tmp;
        this.color.g = (tmp - 0.03F);
        this.color.b = (tmp - 0.07F);
        this.scale = (MathUtils.random(16.0F, 30.0F) * Settings.scale);
        this.canDone = true;
        this.firstCannel = true;
    }

    public void update() {

        this.rotation += this.aV * Gdx.graphics.getDeltaTime();
        if (this.startingDuration - this.duration < 1.0F) {
            this.color.a = Interpolation.fade.apply(0.0F, this.targetAlpha, this.startingDuration - this.duration);
        } else if (this.duration < 1.0F) {
            this.color.a = Interpolation.fade.apply(this.targetAlpha, 0.0F, 1.0F - this.duration);
            if(this.firstCannel){
                this.canDone = false;
                this.firstCannel = false;
            }
        }

        if(this.canDone) {
            this.duration -= Gdx.graphics.getDeltaTime();
        }
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void doneEffect(){
        this.canDone = true;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        if ((this.flipX) && (!this.img.isFlipX())) {
            this.img.flip(true, false);
        } else if ((!this.flipX) && (this.img.isFlipX())) {
            this.img.flip(true, false);
        }
        if ((this.flipY) && (!this.img.isFlipY())) {
            this.img.flip(false, true);
        } else if ((!this.flipY) && (this.img.isFlipY())) {
            this.img.flip(false, true);
        }
        sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
    }

    public void dispose(){}
}
