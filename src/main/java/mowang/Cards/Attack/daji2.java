package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class daji2 extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(daji2.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public daji2() {
        super(ID, daji2.class.getSimpleName(),
                cardStrings, 1, ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.setupDamage(6);
        this.tags.add(CardTags.STARTER_STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3); // 将该卡牌的伤害提高3点。
    }
}
