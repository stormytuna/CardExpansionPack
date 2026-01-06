package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class LastResort extends BaseCard {
    public static final String ID = makeID("LastResort");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.ATTACK,
        CardRarity.RARE,
        CardTarget.ENEMY,
        2
    );

    public LastResort() {
        super(ID, INFO);
        setCostUpgrade(1);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new LastResort();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();

        baseDamage = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(p, damage);
        addToBot(new DamageAction(m, info, AttackEffect.SLASH_DIAGONAL));

        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }
}

