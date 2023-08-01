package mowang.Patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import mowang.Cards.Skill.SeeYouOtherside;

import java.util.Iterator;

public class SYO_ToDiscardPileActionPatch {
	public SYO_ToDiscardPileActionPatch() {
	}

	@SpirePatch(
			clz = CardGroup.class,
			method = "addToBottom"
	)
	public static class PatchAddCardToDiscardBottom {
		public PatchAddCardToDiscardBottom() {
		}

		public static void Postfix(CardGroup group, AbstractCard card) {
			if (group.type == CardGroupType.DISCARD_PILE) {
				if (card instanceof SeeYouOtherside){
					SeeYouOtherside syo = (SeeYouOtherside) card;
					syo.gainMoreBlock();
				}
			}
		}
	}

	@SpirePatch(
			clz = CardGroup.class,
			method = "addToTop"
	)
	public static class PatchAddCardToDiscardTop {
		public PatchAddCardToDiscardTop() {
		}

		public static void Postfix(CardGroup group, AbstractCard card) {
			if (group.type == CardGroupType.DISCARD_PILE) {
				if (card instanceof SeeYouOtherside){
					SeeYouOtherside syo = (SeeYouOtherside) card;
					syo.gainMoreBlock();
				}
			}

		}
	}
}
