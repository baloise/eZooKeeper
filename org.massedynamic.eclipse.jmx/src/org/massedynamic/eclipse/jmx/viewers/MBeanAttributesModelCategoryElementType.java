/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.massedynamic.eclipse.jmx.viewers;

import org.eclipse.swt.graphics.Image;

import org.massedynamic.eclipse.core.viewers.AbstractDataModelCategoryElementType;
import org.massedynamic.eclipse.jmx.JmxActivator;

/**
 * TODO: Comment.
 * 
 * @author Mark Masse
 */
public class MBeanAttributesModelCategoryElementType extends AbstractDataModelCategoryElementType {

    @Override
    public Image getImage(Object element) {
        return JmxActivator.getManagedImage(JmxActivator.IMAGE_KEY_OBJECT_MBEAN_ATTRIBUTES);
    }

}
