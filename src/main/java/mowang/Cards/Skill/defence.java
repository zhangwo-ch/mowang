package mowang.Cards.Skill;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

public class defence extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(defence.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public defence() {
        super(ID, defence.class.getSimpleName(),
                cardStrings, 1, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.NONE);
        setupBlock(5);
        this.tags.add(AbstractCard.CardTags.STARTER_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeBlock(3);
    }
}