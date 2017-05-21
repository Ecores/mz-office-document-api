/*
 * MZ Office Document API
 *
 * Moritz Riebe und Andreas Zaschka GbR
 *
 * Copyright (C) 2016,   Moritz Riebe     (moritz.riebe@mz-solutions.de)
 *                       Andreas Zaschka  (andreas.zaschka@mz-solutions.de)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mz.solutions.office.model.images;

import com.mz.solutions.office.extension.ExtendedValue;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Platzhalter-Wert {@link ExtendedValue} eines Bildes mit einer zugeordneten Resource
 * (Bild-Resource {@link ImageResource}) und ggf erweiterten Parametern/Einstellungen zum Bild.
 * 
 * @author Riebe, Moritz (moritz.riebe@mz-solutions.de)
 */
public class ImageValue extends ExtendedValue {

    private final ImageResource imgResource;
    
    @Nullable
    private String title, description;
    
    /** Flag ob bestehende Abmaßungen überschrieben werden sollen. */
    private boolean overwriteDimension = false;
    
    /** Breite des Bildes in Millimeter. */
    private double width = UnitOfLength.CENTIMETERS.toMillimeters(3.0D);
    
    /** Höhe des Bildes in Millimeter. */
    private double height = UnitOfLength.CENTIMETERS.toMillimeters(1.0D);
    
    public ImageValue(ImageResource imgResource) {
        this.imgResource = Objects.requireNonNull(imgResource, "imgResource");
    }
    
    public ImageResource getImageResource() {
        return imgResource;
    }
    
    public ImageValue setTitle(@Nullable CharSequence title) {
        this.title = (null == title) ? null : title.toString();
        return this;
    }
    
    public ImageValue setDescription(@Nullable CharSequence description) {
        this.description = (null == description) ? null : description.toString();
        return this;
    }
    
    public ImageValue setDimension(double width, double height, UnitOfLength unit) {
        Objects.requireNonNull(unit, "unit");
        
        if (width <= 0.0D) {
            throw new IllegalArgumentException("width <= 0.0");
        }
        
        if (height <= 0.0D) {
            throw new IllegalArgumentException("height <= 0.0");
        }
        
        this.width = unit.toMillimeters(width);
        this.height = unit.toMillimeters(height);
        
        return this;
    }
    
    public ImageValue setOverwriteDimension(boolean overwriteExistingDimension) {
        this.overwriteDimension = overwriteExistingDimension;
        return this;
    }
    
    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }
    
    public double getWidth() {
        return this.width;
    }
    
    public double getHeight() {
        return this.height;
    }

    public boolean isOverwriteDimension() {
        return overwriteDimension;
    }

    @Override
    public String altString() {
        return getDescription().orElse(null);
    }
    
}
