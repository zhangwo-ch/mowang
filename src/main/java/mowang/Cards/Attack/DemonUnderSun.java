package mowang.Cards.Attack;

import mowang.Action.AbsorbingChopAction;
import mowang.Action.EmptyAction;
import mowang.Cards.Skill.Lantern;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class DemonUnderSun extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(DemonUnderSun.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public DemonUnderSun() {
        super(ID, DemonUnderSun.class.getSimpleName(),
                cardStrings, 2, ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 23;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	int times = 1;
    	if(ModHelper.CanReturn(23)) times+=2;
    	else if(ModHelper.CanReturn(13)) times +=1;
    	
    	if(times>1) ModHelper.CanReturn(1,new EmptyAction());
    	
    	for(int i=0;i<times;i++)
    		addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	
    	for(int i=0;i<3;i++)
    		addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(new Burn(), true));
    }
    
    public void triggerOnGlowCheck() {
		if (ModHelper.CanReturn(23)) {
			this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
		} else if(ModHelper.CanReturn(13)){
			this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
		} else
			this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
	}
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(6);
    }
}
