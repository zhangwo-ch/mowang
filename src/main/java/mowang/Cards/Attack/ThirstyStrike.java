package mowang.Cards.Attack;

import mowang.Action.AbsorbingChopAction;
import mowang.Cards.Skill.Lantern;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
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

public class ThirstyStrike extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(ThirstyStrike.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public ThirstyStrike() {
        super(ID, ThirstyStrike.class.getSimpleName(),
                cardStrings, 4, ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        this.damage = this.baseDamage = 13;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	addToBot((AbstractGameAction)new HealAction((AbstractCreature)p, p, this.magicNumber));
    }
    
    @Override
    public void update() {
        super.update();
        int temp = this.cost - ModHelper.ReturnCount;
        if(temp < 0) temp = 0;
        if(temp != 4) this.isCostModifiedForTurn = true;
        this.costForTurn = temp;
    }
    
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(5);
        this.upgradeMagicNumber(1);
    }
}
