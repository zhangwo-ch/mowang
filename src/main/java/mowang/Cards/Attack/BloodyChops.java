package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import mowang.Cards.AbstractHealCard;
import mowang.Helpers.ModHelper;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class BloodyChops extends AbstractHealCard {
    public static final String ID = ModHelper.MakePath(BloodyChops.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public BloodyChops() {
        super(ID, BloodyChops.class.getSimpleName(),
                cardStrings, 2, ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, null);
        this.damage = this.baseDamage = 3;
        this.magicNumber = this.baseMagicNumber = 5;
        this.action = new AbstractGameAction() {
        	@Override
        	public void update() {
        		addToBot(new ModifyDamageAction(uuid, magicNumber));
        		this.isDone = true;
        	}
        };
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)p, (AbstractCreature)p, 3));
    	for(int i=0;i<this.magicNumber;i++) {
    		addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	}
    }
    	
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        //this.upgradeDamage(4);
        this.upgradeMagicNumber(1);
    }
}
