package mowang.Cards.Attack;

import mowang.Action.AbsorbingChopAction;
import mowang.Action.EmptyAction;
import mowang.Cards.Skill.Lantern;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class EarthCollapse extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(EarthCollapse.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public EarthCollapse() {
        super(ID, EarthCollapse.class.getSimpleName(),
                cardStrings, 2, ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 12;
        this.magicNumber = this.baseMagicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	if (ModHelper.CanReturn(11,new EmptyAction())){
    		for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
    			if(!mo.isDead) {
    				addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)mo, -this.magicNumber), -this.magicNumber));
    				if (mo != null && !mo.hasPower("Artifact")) {
    					addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new GainStrengthPower((AbstractCreature)mo, this.magicNumber), this.magicNumber));
    				}
    			}
    		}
    	}
    }
    
    public void triggerOnGlowCheck() {
		if (ModHelper.CanReturn(11)) {
			this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
		} else
			this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
	}
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(5);
        this.upgradeMagicNumber(1);
    }
}
