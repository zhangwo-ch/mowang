package mowang.Cards.demo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.ActionAction;
import mowang.Action.MarchAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class march extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(march.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public march() {
        super(ID, true,
                cardStrings, 1, ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MarchAction(m));
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
    }
}
