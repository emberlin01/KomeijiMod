package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class WumiGaWareruPower extends AbstractPower {
    public static final String POWER_ID = "WumiGaWareruPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("WumiGaWareruPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int damage;
    private AbstractPlayer p = AbstractDungeon.player;

    public WumiGaWareruPower(AbstractCreature owner, int amount,int damage) {
        this.name = NAME;
        this.ID = "WumiGaWareruPower";
        this.owner = owner;
        this.amount = amount;
        this.img = ImageMaster.loadImage("images/power/32/WumiGaWareruPower.png");
        this.type = PowerType.BUFF;
        this.damage = damage;
        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            if(this.amount > 1){
                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
                    AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                    if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    }
                }
                flash();
                this.amount -= 1;
            }
            else
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner,this.owner,"WumiGaWareruPower"));
        }
    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.damage + DESCRIPTIONS[2];
    }
}
