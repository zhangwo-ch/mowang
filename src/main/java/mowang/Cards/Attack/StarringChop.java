package mowang.Cards.Attack;

import mowang.Action.DrawFromDiscard;
import mowang.Action.WraithStrikeHealAction;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import mowang.Cards.AbstractHealCard;
import mowang.Helpers.ModHelper;
import mowang.powers.StarringChopPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class StarringChop extends AbstractHealCard {
    public static final String ID = ModHelper.MakePath(StarringChop.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public StarringChop() {
        super(ID, StarringChop.class.getSimpleName(),
                cardStrings, 1, ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, null);
        this.damage = this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 2;
        this.action = new AbstractGameAction() {
        	@Override
        	public void update() {
        		addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StarringChopPower(AbstractDungeon.player, magicNumber), magicNumber));
        		this.isDone = true;
        	}
        };
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	addToBot(new DrawFromDiscard(this.magicNumber));
    	
    	for(int i=0;i<this.magicNumber;i++)
    		addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(new Burn(), true));
    }
    
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(4);
        this.upgradeMagicNumber(1);
    }
}
