package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class GatheringChop extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(GatheringChop.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public GatheringChop() {
        super(ID, GatheringChop.class.getSimpleName(),
                cardStrings, 2, ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.setupDamage(10);
        setupMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (ModHelper.CanReturn(5)){
            this.addToBot(new GainEnergyAction(magicNumber));
        }
    }


    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3); // 将该卡牌的伤害提高3点。
        upgradeMagicNumber(1);
    }
}
