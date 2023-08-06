package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;

import mowang.Cards.AbstractExampleCard;
import mowang.Action.CallBackAction;
import mowang.Action.EmptyAction;
import mowang.Action.WraithStrikeHealAction;
import mowang.Helpers.ModHelper;
import mowang.powers.ServitorPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class WraithStrike extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(WraithStrike.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public WraithStrike() {
        super(ID, WraithStrike.class.getSimpleName(),
                cardStrings, 2, ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.tags.add(AbstractCard.CardTags.STRIKE);
        this.damage = this.baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	if (ModHelper.CanReturn(22,new EmptyAction())){
    		if(p.hasPower(ServitorPower.POWER_ID)) {
    			this.damage += p.getPower(ServitorPower.POWER_ID).amount;
    		}
    	}
    	
        for (int i = 0; i < 3; i++) {
        	AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        	addToBot(new DamageAction(target, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        
    }

    public void triggerOnGlowCheck() {
		if (ModHelper.CanReturn(22)) {
			this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
		} else
			this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
	}
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(2);
        this.upgradeMagicNumber(1);
    }
}
